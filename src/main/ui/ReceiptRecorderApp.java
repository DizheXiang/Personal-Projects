package ui;

import model.Budget;
import model.ReceiptRecorder;

import java.util.Scanner;

// Receipt recorder application
public class ReceiptRecorderApp {

    private ReceiptRecorder receiptRecorder;
    private Budget budget;
    private Scanner input;
    private static final String selectYourOperation =
            "You want to: \n"
            + "(1) Add a new receipt \n"
            + "(2) Change an existed receipt \n"
            + "(3) Delete an existed receipt \n"
            + "(4) Add a new budget \n"
            + "(5) Change an existed budget \n"
            + "(6) Delete an existed budget \n"
            + "(7) See your expenses in a day \n"
            + "(8) Check whether exceed your budget"
            + "(9) Save and quit recorder";

    // EFFECTS: runs the receipt recorder application
    public ReceiptRecorderApp() {
        runReceiptRecorder();
    }

    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initial() {
        receiptRecorder = new ReceiptRecorder();
        budget = new Budget(0.00, "01/01/2021", "01/01/2021");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user's command or quit app
    private void runReceiptRecorder() {
        boolean keepRunning = true;
        String operation;

        initial();

        while (keepRunning) {
            System.out.println(selectYourOperation);
            operation = input.nextLine();

            if (operation.equals("9")) {
                keepRunning = false;
            } else {
                processOperation(operation);
            }
        }
        System.out.println("\nHave a nice day, goodbye!");
    }

    // EFFECTS: go to different operations based on user's choice
    @SuppressWarnings("methodlength")
    private void processOperation(String operation) {
        switch (operation) {
            case "1":
                createNewReceipt();
                break;
            case "2":
                changeReceipt();
                break;
            case "3":
                deleteReceipt();
                break;
            case "4":
                addBudget();
                break;
            case "5":
                changeBudget();
                break;
            case "6":
                deleteBudget();
                break;
            case "7":
                checkExpenses();
                break;
            case "8":
                checkBudget();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new receipt
    private void createNewReceipt() {
        System.out.println("Enter your new receipt in $: ");
        double amount = input.nextDouble();
        System.out.println("What did you buy: ");
        String item = input.nextLine();
        System.out.println("What is the date(MM/DD/YYYY): ");
        String date = input.nextLine();
        receiptRecorder.addReceipt(amount,item,date);
    }

    // MODIFIES: this
    // EFFECTS: change an existed receipt
    private void changeReceipt() {
        System.out.println("Enter your receipt date(MM/DD/YYYY): ");
        String date = input.nextLine();
        System.out.println("Enter what did you buy: ");
        String item = input.nextLine();
        receiptRecorder.findReceipt(date, item);

        System.out.println("What do you want to change? amount/item/date/nothing");
        while (!input.nextLine().equals("nothing")) {
            makeChangeForReceipt();
        }
    }

    // MODIFIES: this
    // EFFECTS: delete an existed receipt
    private void deleteReceipt() {
        System.out.println("Enter the date of receipt you want to delete(MM/DD/YYYY)");
        String date = input.nextLine();
        System.out.println("Enter the item of receipt you want to delete");
        String item = input.nextLine();
        receiptRecorder.findReceipt(date, item);
        receiptRecorder.removeReceipt();
    }

    // MODIFIES: this
    // EFFECTS: create a budget
    private void addBudget() {
        System.out.println("Enter your preferred budget amount");
        String budgetAmount = input.nextLine();
        System.out.println("Enter the start date of budget(MM/DD/YYYY)");
        String startDate = input.nextLine();
        System.out.println("Enter the end date of budget(MM/DD/YYYY)");
        String endDate = input.nextLine();
        budget.addBudget(budgetAmount,startDate,endDate);
    }

    // MODIFIES: this
    // EFFECTS: change the budget
    private void changeBudget() {
        System.out.println("Enter what do you want to change? amount/item/start date/end date/nothing");
        while (!input.nextLine().equals("nothing")) {
            makeChangeForBudget();
        }
    }

    // MODIFIES: this
    // EFFECTS: delete the budget
    private void deleteBudget() {
        System.out.println("Your budget has been deleted");
        budget.removeBudget();
    }

    // MODIFIES: this
    // EFFECTS: output the total amount of spending in a given time period
    private void checkExpenses() {
        System.out.println("Enter the start date(MM/DD/YYYY): ");
        String startDate = input.nextLine();
        System.out.println("Enter the end date(MM/DD/YYYY): ");
        String endDate = input.nextLine();
        double totalAmount = receiptRecorder.checkExpenses(startDate,endDate);
        System.out.println("Your total expenses amount is " + totalAmount + " dollars");
    }

    // MODIFIES: this
    // EFFECTS: check whether spending exceeds setting budget
    private void checkBudget() {
        budget.checkBudget();
        if (budget.checkBudget()) {
            System.out.println("Great, your spending doesn't exceed your budget");
        } else {
            System.out.println("Be careful, your spending exceeds your budget");
        }
    }

    // MODIFIES: this
    // EFFECTS: update the information about chosen receipt
    private void makeChangeForReceipt() {
        if (input.nextLine().equals("amount")) {
            double newAmount = input.nextDouble();
            receiptRecorder.changeAmount(newAmount);
        } else if (input.nextLine().equals("item")) {
            String newItem = input.nextLine();
            receiptRecorder.changeItem(newItem);
        } else if (input.nextLine().equals("date")) {
            String newDate = input.nextLine();
            receiptRecorder.changeDate(newDate);
        }
    }

    // MODIFIES: this
    // EFFECTS: update the information about budget
    private void makeChangeForBudget() {
        if (input.nextLine().equals("amount")) {
            double newAmount = input.nextDouble();
            budget.changeAmount(newAmount);
        } else if (input.nextLine().equals("item")) {
            String newItem = input.nextLine();
            budget.changeItem(newItem);
        } else if (input.nextLine().equals("start date")) {
            String newStartDate = input.nextLine();
            budget.changeStartDate(newStartDate);
        } else if (input.nextLine().equals("end date")) {
            String newEndDate = input.nextLine();
            budget.changeEndDate(newEndDate);
        }
    }
}
