package az.developia.transferer.bank;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.StringJoiner;

public class Account {
    private String iban;
    private Amount balance;

    public Account(String iban, Amount balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public Amount getBalance() {
        return balance;
    }

    public void changeBalance(Amount payment, boolean isIncrease) {
        var signature = isIncrease ? 1 : -1;
        var increasedOrDecreasedBalance = this.balance.getValue()
                .add(payment.getValue()
                        .multiply(BigDecimal.valueOf(signature))
                );
        this.balance.setValue(increasedOrDecreasedBalance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(iban, account.iban) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("iban='" + iban + "'")
                .add("balance=" + balance)
                .toString();
    }
}
