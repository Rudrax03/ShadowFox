package com.bankapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private final String accountHolder;
    private double balance;
    private final List<String> transactionHistory;

    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created for " + accountHolder);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(String.format("Deposited: $%.2f", amount));
        } else {
            transactionHistory.add(String.format("Failed deposit attempt: $%.2f", amount));
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(String.format("Withdrew: $%.2f", amount));
        } else {
            transactionHistory.add(String.format("Failed withdrawal attempt: $%.2f", amount));
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
