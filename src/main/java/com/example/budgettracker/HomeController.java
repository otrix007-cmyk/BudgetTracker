
package com.example.budgettracker;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ExpenseManager expenseManager;
    private final BudgetProfileRepository budgetProfileRepository;

    public HomeController(
            ExpenseManager expenseManager,
            BudgetProfileRepository budgetProfileRepository) {
        this.expenseManager = expenseManager;
        this.budgetProfileRepository = budgetProfileRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        updateModel(model, session);
        return "index";
    }

    @PostMapping("/selectUser")
    public String selectUser(
            @RequestParam("username") String username,
            HttpSession session) {

        String cleanedUsername = username.trim();

        if (!cleanedUsername.isEmpty()
                && cleanedUsername.length() <= 50) {
            session.setAttribute("username", cleanedUsername);
        }

        return "redirect:/";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("income") double income,
            Model model,
            HttpSession session) {

        String username = getUsername(session);

        if (username == null) {
            model.addAttribute("error",
                    "Please select a user first.");
        } else if (!Double.isFinite(income) || income < 0) {
            model.addAttribute("error",
                    "Please enter a valid monthly income.");
        } else {
            BudgetProfile profile = budgetProfileRepository
                    .findById(username)
                    .orElse(new BudgetProfile(username, income));

            profile.setMonthlyIncome(income);
            budgetProfileRepository.save(profile);
        }

        updateModel(model, session);
        return "index";
    }

    @PostMapping("/addExpense")
    public String addExpense(
            @RequestParam("name") String name,
            @RequestParam("amount") double amount,
            @RequestParam("category") ExpenseCategory category,
            Model model,
            HttpSession session) {

        String username = getUsername(session);

        if (username == null) {
            model.addAttribute("error",
                    "Please select a user first.");
        } else if (name == null || name.isBlank()
                || !Double.isFinite(amount)
                || amount <= 0) {
            model.addAttribute("error",
                    "Please enter a valid expense.");
        } else {
            expenseManager.addExpense(
                    username,
                    new Expense(name, amount, category)
            );
        }

        updateModel(model, session);
        return "index";
    }

    private String getUsername(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    private void updateModel(Model model, HttpSession session) {

        String username = getUsername(session);

        double monthlyIncome = 0;

        if (username != null) {
            monthlyIncome = budgetProfileRepository
                    .findById(username)
                    .map(BudgetProfile::getMonthlyIncome)
                    .orElse(0.0);
        }

        BudgetCalculator calculator =
                new BudgetCalculator(monthlyIncome);

        model.addAttribute("username", username);
        model.addAttribute("income", monthlyIncome);

        model.addAttribute("needs",
                calculator.getNeedsBudget());
        model.addAttribute("wants",
                calculator.getWantsBudget());
        model.addAttribute("savings",
                calculator.getSavingsBudget());

        if (username != null) {
            model.addAttribute("expenses",
                    expenseManager.getExpenses(username));

            model.addAttribute("spentNeeds",
                    expenseManager.getTotalByCategory(
                            username, ExpenseCategory.NEEDS));

            model.addAttribute("spentWants",
                    expenseManager.getTotalByCategory(
                            username, ExpenseCategory.WANTS));

            model.addAttribute("spentSavings",
                    expenseManager.getTotalByCategory(
                            username, ExpenseCategory.SAVINGS));
        } else {
            model.addAttribute("spentNeeds", 0.0);
            model.addAttribute("spentWants", 0.0);
            model.addAttribute("spentSavings", 0.0);
        }
    }
}
