package az.developia.transferer.bank;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Customer {
    private String cif;
    private String companyName;
    private List<Account> accounts;

    public Customer(String cif, String companyName, List<Account> accounts) {
        this.cif = cif;
        this.companyName = companyName;
        this.accounts = accounts;
    }

    public String getCif() {
        return cif;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(cif, customer.cif) && Objects.equals(companyName, customer.companyName) && Objects.equals(accounts, customer.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, companyName, accounts);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
                .add("cif='" + cif + "'")
                .add("companyName='" + companyName + "'")
                .add("accounts=" + accounts)
                .toString();
    }
}
