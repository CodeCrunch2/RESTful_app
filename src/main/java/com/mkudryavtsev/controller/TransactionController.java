package com.mkudryavtsev.controller;

import com.mkudryavtsev.model.Transaction;
import com.mkudryavtsev.repository.TransactionRepository;
import com.mkudryavtsev.repository.hibernate.HibernateTransactionRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@WebServlet("/transactions")
public class TransactionController extends HttpServlet {
    TransactionRepository transactionRepository;

    public void init() throws ServletException {
        transactionRepository = new HibernateTransactionRepositoryImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transaction> transactionList = transactionRepository.getAll();
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        for (Transaction transaction: transactionList) {
            writer.println(transaction);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amount = request.getParameter("amount");
        Transaction transaction = new Transaction(new Date(), null, new BigDecimal(amount));
        transactionRepository.save(transaction);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = transactionRepository.getById(id);
        String amount = request.getParameter("amount");
        transaction.setAmount(new BigDecimal(amount));
        transaction.setUpdated(new Date());
        transactionRepository.update(transaction);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Transaction transaction = transactionRepository.getById(id);
        transactionRepository.delete(transaction);
    }

    public void destroy() {

    }
}
