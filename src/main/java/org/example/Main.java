package org.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(new Account(12345, 1000));
        bank.addAccount(new Account(67890, 500));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ATMGUI atmGUI = new ATMGUI(bank);
                atmGUI.display();
            }
        });
    }
}