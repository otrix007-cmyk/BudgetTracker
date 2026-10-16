
package com.example.budgettracker;

public class BudgetCalculator implements BudgetReport {

    private double monthlyIncome;

    public BudgetCalculator(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public double getNeedsBudget() {
        return monthlyIncome * 0.50;
    }

    public double getWantsBudget() {
        return monthlyIncome * 0.30;
    }

    public double getSavingsBudget() {
        return monthlyIncome * 0.20;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }
}
