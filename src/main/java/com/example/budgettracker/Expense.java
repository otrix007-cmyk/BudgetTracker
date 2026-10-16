
package com.example.budgettracker;

public class Expense {

    private String name;
    private double amount;
    private ExpenseCategory category;

    public Expense(String name, double amount,
                   ExpenseCategory category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }
}
