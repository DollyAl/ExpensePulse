import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String category;

    Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}

public class Main{
    public static void main(String[] args) {
        ArrayList<Expense> expenses = new ArrayList<>();
        Map<String, Double> categoryTotals = new HashMap<>();
        double totalExpenses = 0.0;

        Scanner scanner = new Scanner(System.in);
        boolean continueAdding = true;

        while (continueAdding) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses by Category");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter expense description (or 'done' to finish): ");
                    String description = scanner.nextLine();

                    if (description.equalsIgnoreCase("done")) {
                        continueAdding = false;
                    } else {
                        System.out.print("Enter expense amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter expense category: ");
                        String category = scanner.nextLine();

                        Expense expense = new Expense(description, amount, category);
                        expenses.add(expense);

                        totalExpenses += amount;

                        categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);

                        System.out.println("Expense added: " + description + " $" + amount + " (" + category + ")");
                    }
                    break;

                case 2:
                    System.out.println("Expenses by Category:");
                    for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                        System.out.println(entry.getKey() + ": $" + entry.getValue());
                    }
                    break;

                case 3:
                    continueAdding = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        // Display the list of expenses
        System.out.println("\nExpense List:");
        for (Expense expense : expenses) {
            System.out.println(expense.description + ": $" + expense.amount + " (" + expense.category + ")");
        }

        // Show the total expenses
        System.out.println("Total Expenses: $" + totalExpenses);

        scanner.close();
    }
}
