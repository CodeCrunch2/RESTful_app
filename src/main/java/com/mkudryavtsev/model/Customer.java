package com.mkudryavtsev.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Customers")
public class Customer extends BaseEntity{
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(account, customer.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, account);
    }

    @Override
    public String toString() {
        return "Customer " +
                "id: " + this.getId() +
                ", first name: " + firstName +
                ", last name: " + lastName +
                ", account id: " + account.getId();
    }
}
