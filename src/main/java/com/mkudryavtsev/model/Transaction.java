package com.mkudryavtsev.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Transactions")
public class Transaction extends BaseEntity {
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(Date created, Date updated, BigDecimal amount) {
        this.created = created;
        this.updated = updated;
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(created, that.created) &&
                Objects.equals(updated, that.updated) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, updated, amount);
    }

    @Override
    public String toString() {
        return "Transaction " +
                "id: " + this.getId() +
                ", created: " + created +
                ", updated: " + updated +
                ", amount: " + amount;
    }
}
