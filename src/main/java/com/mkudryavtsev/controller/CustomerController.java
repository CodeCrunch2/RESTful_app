package com.mkudryavtsev.controller;

import com.mkudryavtsev.model.Account;
import com.mkudryavtsev.model.Customer;
import com.mkudryavtsev.repository.AccountRepository;
import com.mkudryavtsev.repository.CustomerRepository;
import com.mkudryavtsev.repository.hibernate.HibernateAccountRepositoryImpl;
import com.mkudryavtsev.repository.hibernate.HibernateCustomerRepositoryImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {
    CustomerRepository customerRepository;
    AccountRepository accountRepository;
    public void init() throws ServletException {
        customerRepository = new HibernateCustomerRepositoryImpl();
        accountRepository = new HibernateAccountRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerRepository.getAll();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        for(Customer customer: customerList) {
            writer.println(customer);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        Customer customer = new Customer(firstName, lastName, null);
        customerRepository.save(customer);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerRepository.getById(id);
        if (req.getParameter("firstName") != null) {
            String firstName = req.getParameter("firstName");
            customer.setFirstName(firstName);
        }else if (req.getParameter("lastName") != null) {
            String lastName = req.getParameter("lastName");
            customer.setLastName(lastName);
        }else if (req.getParameter("account_id") != null) {
            Integer accountId = Integer.parseInt(req.getParameter("account_id"));
            Account account = accountRepository.getById(accountId);
            customer.setAccount(account);
        }
        customerRepository.update(customer);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Customer customer = customerRepository.getById(id);
        customerRepository.delete(customer);
    }
}
