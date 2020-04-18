package com.mkudryavtsev.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
public class Account extends BaseEntity {
    @Column(name = "balance")
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Accounts_Transactions",
    joinColumns = @JoinColumn(name = "account_id"),
    inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private List<Transaction> transactions;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Account() {
    }

    public Account(BigDecimal balance, List<Transaction> transactions, Status status) {
        this.balance = balance;
        this.transactions = transactions;
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(balance, account.balance) &&
                Objects.equals(transactions, account.transactions) &&
                status == account.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, transactions, status);
    }

    @Override
    public String toString() {
        return "Account " +
                "id: " + this.getId() +
                ", balance: " + balance +
                ", status: " + status;
    }
}
