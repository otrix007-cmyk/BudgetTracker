
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.budgettracker.Expense" %>
<!DOCTYPE html>
<html>
<head>
    <title>BudgetTracker</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f4f8;
            margin: 0;
            padding: 30px 15px;
        }
        .container {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px #0002;
        }
        h1 { color: #236b52; }
        h1, h2, .intro { text-align: center; }
        form { text-align: center; margin: 20px 0; }
        input, select, button {
            padding: 12px;
            margin: 5px;
            font-size: 15px;
            max-width: 100%;
            box-sizing: border-box;
        }
        button {
            background: #236b52;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        button:hover { background: #18503c; }
        .budget {
            background: #e8f4ee;
            padding: 15px;
            margin: 10px 0;
            border-radius: 8px;
        }
        .user-box {
            background: #dceee6;
            padding: 15px;
            border-radius: 8px;
            text-align: center;
        }
        .error { color: #b42318; text-align: center; }
        hr { margin: 30px 0; border: 1px solid #eee; }
        table { width: 100%; border-collapse: collapse; }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
    </style>
</head>
<body>
<div class="container">

    <h1>BudgetTracker</h1>
    <h2>Your 50/30/20 Budgeting Assistant</h2>
    <p class="intro">Take control of your finances!</p>

    <div class="user-box">
        <h2>Select User</h2>

        <form action="${pageContext.request.contextPath}/selectUser"
              method="post">

            <input type="text" name="username"
                   placeholder="Enter username"
                   maxlength="50" required>

            <button type="submit">Select User</button>
        </form>

        <p>Current User:
            <strong>${username}</strong>
        </p>
    </div>

    <p class="error">${error}</p>

    <hr>

    <h2>Monthly Income</h2>

    <form action="${pageContext.request.contextPath}/calculate"
          method="post">

        <label for="income">Monthly Income ($)</label>

        <input type="number" id="income" name="income"
               min="0" step="0.01" required
               value="${income}">

        <button type="submit">Calculate Budget</button>
    </form>

    <h2>Your Monthly Budget</h2>
    <div class="budget">Needs (50%): $${needs}</div>
    <div class="budget">Wants (30%): $${wants}</div>
    <div class="budget">Savings (20%): $${savings}</div>

    <hr>

    <h2>Add an Expense</h2>

    <form action="${pageContext.request.contextPath}/addExpense"
          method="post">

        <input type="text" name="name"
               placeholder="Expense name"
               maxlength="100" required>

        <input type="number" name="amount"
               placeholder="Amount ($)"
               min="0.01" step="0.01" required>

        <select name="category" required>
            <option value="NEEDS">Needs</option>
            <option value="WANTS">Wants</option>
            <option value="SAVINGS">Savings</option>
        </select>

        <button type="submit">Add Expense</button>
    </form>

    <hr>

    <h2>Spending Summary</h2>
    <div class="budget">Needs Spent: $${spentNeeds}</div>
    <div class="budget">Wants Spent: $${spentWants}</div>
    <div class="budget">Savings Allocated: $${spentSavings}</div>

    <h2>Expense History</h2>

    <table>
        <tr>
            <th>Name</th>
            <th>Amount</th>
            <th>Category</th>
        </tr>

        <%
            List<Expense> expenses =
                (List<Expense>) request.getAttribute("expenses");

            if (expenses != null) {
                for (Expense expense : expenses) {
        %>
        <tr>
            <td><%= expense.getName()
                    .replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;") %></td>
            <td>$<%= expense.getAmount() %></td>
            <td><%= expense.getCategory() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

</div>
</body>
</html>
