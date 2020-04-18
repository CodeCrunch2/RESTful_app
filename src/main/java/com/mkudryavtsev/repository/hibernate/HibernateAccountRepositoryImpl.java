package com.mkudryavtsev.repository.hibernate;

import com.mkudryavtsev.model.Account;
import com.mkudryavtsev.repository.AccountRepository;
import com.mkudryavtsev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository {
    @Override
    public void save(Account account) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Account account) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(account);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Account account) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(account);
        transaction.commit();
        session.close();
    }

    @Override
    public Account getById(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.get(Account.class, id);
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accountList;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        accountList = session.createQuery("from Account").list();
        transaction.commit();
        return accountList;
    }
}
