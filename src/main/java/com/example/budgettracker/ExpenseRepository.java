
package com.example.budgettracker;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository
        extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByUsername(String username);
}
