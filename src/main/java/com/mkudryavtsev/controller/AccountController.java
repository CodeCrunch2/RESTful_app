package com.mkudryavtsev.controller;

import com.mkudryavtsev.model.Account;
import com.mkudryavtsev.model.Status;
import com.mkudryavtsev.model.Transaction;
import com.mkudryavtsev.repository.AccountRepository;
import com.mkudryavtsev.repository.TransactionRepository;
import com.mkudryavtsev.repository.hibernate.HibernateAccountRepositoryImpl;
import com.mkudryavtsev.repository.hibernate.HibernateTransactionRepositoryImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/accounts")
public class AccountController extends HttpServlet {
    AccountRepository accountRepository;
    TransactionRepository transactionRepository;
    public void init() throws ServletException {
        accountRepository = new HibernateAccountRepositoryImpl();
        transactionRepository = new HibernateTransactionRepositoryImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if (req.getParameter("id") != null) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Account account = accountRepository.getById(id);
            List<Transaction> transactionList = account.getTransactions();
            writer.println(account);
            writer.println("Transactions:");
            for (Transaction transaction: transactionList) {
                writer.println(transaction);
            }
        }else {
            List<Account> accountList = accountRepository.getAll();
            for (Account account: accountList) {
                writer.println(account);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String balance = req.getParameter("balance");
        String statusString = req.getParameter("status");
        Status status = Status.valueOf(statusString);
        Account account = new Account(new BigDecimal(balance), null, status);
        accountRepository.save(account);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Account account = accountRepository.getById(id);
        if (req.getParameter("balance") != null) {
            String balance = req.getParameter("balance");
            account.setBalance(new BigDecimal(balance));
        }else if (req.getParameter("status") != null) {
            String statusString = req.getParameter("status");
            Status status = Status.valueOf(statusString);
            account.setStatus(status);
        }else if(req.getParameter("transaction_id") != null) {
            Integer transId = Integer.parseInt(req.getParameter("transaction_id"));
            Transaction transaction = transactionRepository.getById(transId);
            List<Transaction> transactionList = account.getTransactions();
            if (transactionList == null) {
                transactionList = new ArrayList<>();
                transactionList.add(transaction);
            }else {
                transactionList.add(transaction);
            }
            account.setTransactions(transactionList);
        }
        accountRepository.update(account);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Account account = accountRepository.getById(id);
        account.setStatus(Status.DELETED);
        accountRepository.update(account);
    }
}
