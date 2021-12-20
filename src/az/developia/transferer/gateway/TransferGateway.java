package az.developia.transferer.gateway;

import az.developia.transferer.exception.ValidationException;
import az.developia.transferer.bank.Account;
import az.developia.transferer.bank.Amount;
import az.developia.transferer.bank.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TransferGateway implements Transfer, Registerer {
    private List<Bank> registeredBanks;
    private ReentrantLock transferLock;
    private Condition balanceCondition;

    public TransferGateway() {
        this.registeredBanks = new ArrayList<>();
        this.transferLock = new ReentrantLock();
        this.balanceCondition = transferLock.newCondition();
    }

    public void transfer(Account from, Account to, Amount amount, int tries) {
        for (int i = 0; i < tries; i++) {
            transfer(from, to, amount);
        }
    }

    @Override
    public void transfer(Account from, Account to, Amount amount) {
        delegate(from, to, amount);
    }

    private void delegate(Account from, Account to, Amount amount) {
        var runnable = (Runnable) () -> {
            var threadInfo = String.format("===Thread[%d]===", Thread.currentThread().getId());
            System.out.println(threadInfo + "Started to Transfer===");
            checkAccountIsRegistered(from);
            checkAccountIsRegistered(to);
            checkBalance(from, amount);

            try {
                this.transferLock.lock();
                // TODO critical part. Should syncronize
                if (from.getBalance().getValue().compareTo(amount.getValue()) < 0) {
                    try {
                        balanceCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        balanceCondition.signalAll();
                    }
                } else {
                    balanceCondition.signalAll();
                }
                from.changeBalance(amount, false);
                to.changeBalance(amount, true);

            } finally {
                this.transferLock.unlock();
            }


            System.out.printf(threadInfo + " | Successfully transferred amount: %.2f | from account: %s | to account: %s%n",
                    amount.getValue(), from.getIban(), to.getIban());

            System.out.printf(threadInfo + " | Total balance in account: %s is %.2f %s%n",
                    from.getIban(), from.getBalance().getValue(), from.getBalance().getCurrency());

            System.out.printf(threadInfo + " | Total balance in account: %s is %.2f %s%n",
                    to.getIban(), to.getBalance().getValue(), to.getBalance().getCurrency());

            System.out.println(threadInfo + "Ended Transfer===");
        };

        var task = new Thread(runnable);
        task.start();
        try {
            task.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkAccountIsRegistered(Account account) {
        var threadInfo = String.format("===Thread[%d]===", Thread.currentThread().getId());
        System.out.println(threadInfo + "Checking account registration start: " + account + " ===");
        AtomicBoolean isFound = new AtomicBoolean(false);
        this.registeredBanks
                .forEach(bank -> bank.getCustomers()
                        .forEach(customer -> {
                            if (customer.getAccounts().contains(account)) {
                                isFound.set(true);
                            }
                        })
                );

        if (!isFound.get()) {
            throw new ValidationException(threadInfo + " | account is not registered to transfer gateway");
        }
        System.out.println(threadInfo + "Checking account registration end : " + account + " ===");
    }

    private void checkBalance(Account account, Amount requiredAmount) {
        var threadInfo = String.format("===Thread[%d]===", Thread.currentThread().getId());
        System.out.println(threadInfo + "Checking balance  start: " + account + " ===");
        checkCurrency(account, requiredAmount.getCurrency());
        if (account.getBalance().getValue().compareTo(requiredAmount.getValue()) < 0) {
            throw new ValidationException(threadInfo + " | doesn't have required amount in balance");
        }
        System.out.println(threadInfo + "Checking balance end: " + account + " ===");
    }

    private void checkCurrency(Account account, Amount.Currency requiredCurrency) {
        var threadInfo = String.format("===Thread[%d]===", Thread.currentThread().getId());
        System.out.println(threadInfo + "Checking currency  start: " + account + " ===");
        if (!account.getBalance().getCurrency().equals(requiredCurrency)) {
            throw new ValidationException(threadInfo + " | currency is not same and has to be exchanged");
        }
        System.out.println(threadInfo + "Checking currency  end: " + account + " ===");
    }

    @Override
    public void register(Registrable registrable) {
        this.registeredBanks.add((Bank) registrable);
    }
}
