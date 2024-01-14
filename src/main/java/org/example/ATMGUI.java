package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    private Bank bank;
    private JFrame frame;
    private JTextField accountNumberField, amountField;
    private JTextArea textArea;
    private JButton balanceButton, depositButton, withdrawButton;

    public ATMGUI(Bank bank) {
        this.bank = bank;
        createWindow();
        layoutComponents();
    }

    private void createWindow() {
        frame = new JFrame("ATM Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
    }

    private void layoutComponents() {
        accountNumberField = new JTextField(10);
        amountField = new JTextField(10);
        textArea = new JTextArea();
        textArea.setEditable(false);

        JPanel panel = new JPanel();
        balanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");

        panel.add(new JLabel("Account Number:"));
        panel.add(accountNumberField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(balanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performBalanceCheck();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performDeposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performWithdrawal();
            }
        });
    }

    private void performBalanceCheck() {
        int accountNumber = getAccountNumberFromField();
        if (accountNumber != -1) {
            Account account = bank.getAccount(accountNumber);
            if (account != null) {
                textArea.setText("Balance: " + account.getBalance());
            } else {
                textArea.setText("Account not found.");
            }
        }
    }

    private void performDeposit() {
        int accountNumber = getAccountNumberFromField();
        double amount = getAmountFromField();
        if (accountNumber != -1 && amount != -1) {
            Account account = bank.getAccount(accountNumber);
            if (account != null) {
                bank.deposit(accountNumber, amount);
                textArea.setText("Deposited: " + amount + "\nNew Balance: " + account.getBalance());
            } else {
                textArea.setText("Account not found.");
            }
        }
    }

    private void performWithdrawal() {
        int accountNumber = getAccountNumberFromField();
        double amount = getAmountFromField();
        if (accountNumber != -1 && amount != -1) {
            Account account = bank.getAccount(accountNumber);
            if (account != null) {
                if (bank.withdraw(accountNumber, amount)) {
                    textArea.setText("Withdrawn: " + amount + "\nNew Balance: " + account.getBalance());
                } else {
                    textArea.setText("Insufficient funds.");
                }
            } else {
                textArea.setText("Account not found.");
            }
        }
    }

    private int getAccountNumberFromField() {
        try {
            return Integer.parseInt(accountNumberField.getText());
        } catch (NumberFormatException e) {
            textArea.setText("Invalid account number.");
            return -1;
        }
    }

    private double getAmountFromField() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            textArea.setText("Invalid amount.");
            return -1;
        }
    }

    public void display() {
        frame.setVisible(true);
    }
}

