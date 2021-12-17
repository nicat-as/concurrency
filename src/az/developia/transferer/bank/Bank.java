package az.developia.transferer.bank;

import az.developia.transferer.gateway.Registrable;

import java.util.List;

public class Bank implements Registrable {
    private Long id;
    private String name;
    private List<Customer> customers;

    public Bank(Long id, String name, List<Customer> customers) {
        this.id = id;
        this.name = name;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }



}
