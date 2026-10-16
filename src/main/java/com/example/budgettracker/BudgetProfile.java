
package com.example.budgettracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BudgetProfile {

    @Id
    private String username;

    private double monthlyIncome;

    public BudgetProfile() {
    }

    public BudgetProfile(String username, double monthlyIncome) {
        this.username = username;
        this.monthlyIncome = monthlyIncome;
    }

    public String getUsername() {
        return username;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
