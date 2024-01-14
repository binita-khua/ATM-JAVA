package org.example;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(int accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposited: " + amount + ". New Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Withdrawn: " + amount + ". New Balance: " + account.getBalance());
            return true;
        } else {
            System.out.println("Account not found or insufficient funds.");
            return false;
        }
    }

    public void checkBalance(int accountNumber) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
