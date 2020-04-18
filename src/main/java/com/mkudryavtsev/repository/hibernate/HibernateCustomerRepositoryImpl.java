package com.mkudryavtsev.repository.hibernate;

import com.mkudryavtsev.model.Customer;
import com.mkudryavtsev.repository.CustomerRepository;
import com.mkudryavtsev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCustomerRepositoryImpl implements CustomerRepository {
    @Override
    public void save(Customer customer) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Customer customer) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Customer customer) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList;
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        customerList = session.createQuery("from Customer").list();
        transaction.commit();
        return customerList;
    }

    @Override
    public Customer getById(Integer id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        session.close();
        return customer;
    }
}
