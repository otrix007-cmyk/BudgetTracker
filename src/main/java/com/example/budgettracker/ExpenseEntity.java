
package com.example.budgettracker;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private double amount;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    public ExpenseEntity() {
    }


    public ExpenseEntity(String username, String name,
                         double amount, ExpenseCategory category) {
        this.username = username;
        this.name = name;
        this.amount = amount;
        this.category = category;
    }


    public Long getId() {
        return id;
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

    public String getUsername() {
        return username;
    }
}