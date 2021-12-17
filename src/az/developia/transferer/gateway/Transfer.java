package az.developia.transferer.gateway;

import az.developia.transferer.bank.Account;
import az.developia.transferer.bank.Amount;

public interface Transfer {
    void transfer(Account from, Account to, Amount amount);
}
