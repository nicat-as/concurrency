package az.developia.transferer;

import az.developia.transferer.bank.Account;
import az.developia.transferer.bank.Amount;
import az.developia.transferer.bank.Bank;
import az.developia.transferer.bank.Customer;
import az.developia.transferer.gateway.TransferGateway;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Customer google = new Customer("12345678",
                "Google",
                List.of(new Account("12345678AZN777", new Amount(BigDecimal.valueOf(132000), Amount.Currency.AZN)))
        );
        Bank pashabank = new Bank(1L, "Pasha Bank", List.of(google));


        Customer twitter = new Customer("23456789", "Twitter",
                List.of(new Account("23456789AZN476", new Amount(BigDecimal.valueOf(118000), Amount.Currency.AZN)))
        );
        Bank kapital = new Bank(2L, "Kapital Bank", List.of(twitter));

        var transferGateway = new TransferGateway();
        transferGateway.register(pashabank);
        transferGateway.register(kapital);

        transferGateway.transfer(
                google.getAccounts().get(0),
                twitter.getAccounts().get(0),
                new Amount(BigDecimal.valueOf(35000), Amount.Currency.AZN),
                5
        );

    }
}
