
package com.example.budgettracker;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseManager {

    private final ExpenseRepository expenseRepository;

    public ExpenseManager(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(String username, Expense expense) {
        if (username == null || username.isBlank()
                || expense == null
                || expense.getCategory() == null
                || expense.getName() == null
                || expense.getName().isBlank()
                || !Double.isFinite(expense.getAmount())
                || expense.getAmount() <= 0) {
            throw new IllegalArgumentException("Invalid expense");
        }

        ExpenseEntity entity = new ExpenseEntity(
                username,
                expense.getName(),
                expense.getAmount(),
                expense.getCategory()
        );

        expenseRepository.save(entity);
    }

    public List<Expense> getExpenses(String username) {
        return expenseRepository.findByUsername(username)
                .stream()
                .map(entity -> new Expense(
                        entity.getName(),
                        entity.getAmount(),
                        entity.getCategory()
                ))
                .toList();
    }

    public double getTotalByCategory(
            String username, ExpenseCategory category) {

        return getExpenses(username).stream()
                .filter(expense -> expense.getCategory() == category)
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public double getTotalExpenses(String username) {
        return getExpenses(username).stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}
