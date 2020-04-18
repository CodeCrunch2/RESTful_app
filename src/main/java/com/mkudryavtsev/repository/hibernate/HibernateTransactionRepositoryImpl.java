package com.mkudryavtsev.repository.hibernate;

import com.mkudryavtsev.model.Customer;
import com.mkudryavtsev.model.Transaction;
import com.mkudryavtsev.repository.TransactionRepository;
import com.mkudryavtsev.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class HibernateTransactionRepositoryImpl implements TransactionRepository {
    @Override
    public void save(Transaction transaction) {
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction transactionHibernate = session.beginTransaction();
        session.save(transaction);
        transactionHibernate.commit();
        session.close();
    }

    @Override
    public void delete(Transaction transaction) {
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction transactionHibernate = session.beginTransaction();
        session.delete(transaction);
        transactionHibernate.commit();
        session.close();
    }

    @Override
    public void update(Transaction transaction) {
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction transactionHibernate = session.beginTransaction();
        session.update(transaction);
        transactionHibernate.commit();
        session.close();
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactionList;
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction transactionHibernate = session.beginTransaction();
        transactionList = session.createQuery("from Transaction").list();
        transactionHibernate.commit();
        return transactionList;
    }

    @Override
    public Transaction getById(Integer id) {
        Session session = HibernateUtil.getSession();
        org.hibernate.Transaction transactionHibernate = session.beginTransaction();
        Transaction transaction = session.get(Transaction.class, id);
        transactionHibernate.commit();
        session.close();
        return transaction;
    }
}
