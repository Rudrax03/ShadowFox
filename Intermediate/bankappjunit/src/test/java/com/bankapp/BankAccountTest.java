package com.bankapp;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class BankAccountTest {

    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount("John");
        account.deposit(100);
        assertEquals(100, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawSuccess() {
        BankAccount account = new BankAccount("John");
        account.deposit(200);
        account.withdraw(50);
        assertEquals(150, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawFail() {
        BankAccount account = new BankAccount("John");
        account.deposit(50);
        account.withdraw(100); // More than balance
        assertEquals(50, account.getBalance(), 0.001);
    }

    @Test
    public void testTransactionHistory() {
        BankAccount account = new BankAccount("John");
        account.deposit(100);
        account.withdraw(30);
        List<String> history = account.getTransactionHistory();

        assertTrue(history.get(0).contains("Account created"));
        assertTrue(history.stream().anyMatch(e -> e.contains("Deposited: $100")));
        assertTrue(history.stream().anyMatch(e -> e.contains("Withdrew: $30")));
    }
}
