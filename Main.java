import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean planning = true;
        
        System.out.println("=== Simple Budget Planner ===");
        
        while (planning) {
            // Step 1: Accept Monthly Income
            System.out.print("\nEnter your total monthly income: $");
            double income = scanner.nextDouble();
            scanner.nextLine(); // consume newline
            
            // Step 2: Enter Expenses
            ArrayList<Expense> expenses = new ArrayList<>();
            boolean addingExpenses = true;
            
            System.out.println("\nEnter your expenses (type 'done' when finished):");
            
            while (addingExpenses) {
                System.out.print("Enter expense category (or 'done' to finish): ");
                String category = scanner.nextLine();
                
                if (category.equalsIgnoreCase("done")) {
                    addingExpenses = false;
                } else {
                    System.out.print("Enter amount for " + category + ": $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    
                    expenses.add(new Expense(category, amount));
                }
            }
            
            // Step 3: Calculate Total Expenses
            double totalExpenses = 0;
            System.out.println("\n--- Your Expenses ---");
            for (Expense expense : expenses) {
                System.out.printf("%-15s $%.2f%n", expense.getCategory(), expense.getAmount());
                totalExpenses += expense.getAmount();
            }
            
            // Step 4: Calculate Savings
            double savings = income - totalExpenses;
            
            // Step 5: Display the Budget Summary
            System.out.println("\n--- Budget Summary ---");
            System.out.printf("Total Income:    $%.2f%n", income);
            System.out.printf("Total Expenses:  $%.2f%n", totalExpenses);
            System.out.printf("Savings:         $%.2f%n", savings);
            
            if (savings < 0) {
                System.out.println("\n⚠️ Warning: You are overspending!");
            } else if (savings == 0) {
                System.out.println("\nℹ️ You're breaking even - no savings this month.");
            } else {
                System.out.println("\n✅ Good job! You're within your budget.");
            }
            
            // Step 6: Offer to Plan Again
            System.out.print("\nWould you like to plan another month's budget? (yes/no): ");
            String response = scanner.nextLine();
            
            if (!response.equalsIgnoreCase("yes")) {
                planning = false;
                System.out.println("\nThank you for using Simple Budget Planner. Goodbye!");
            }
        }
        
        scanner.close();
    }
}

class Expense {
    private String category;
    private double amount;
    
    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getAmount() {
        return amount;
    }
}