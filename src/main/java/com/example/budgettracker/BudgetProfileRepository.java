
package com.example.budgettracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetProfileRepository
        extends JpaRepository<BudgetProfile, String> {
}
