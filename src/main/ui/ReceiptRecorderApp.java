package ui;

import model.Receipt;
import model.ReceiptRecorder;

import java.util.Scanner;

// Receipt recorder application
public class ReceiptRecorderApp {

    private ReceiptRecorder receiptRecorder;
    private Receipt receipt;
    private double budget;
    private Scanner input;
    private static final String selectYourOperation =
            "You want to: \n"
                    + "(1) Add a new receipt \n"
                    + "(2) Change an existed receipt \n"
                    + "(3) Delete an existed receipt \n"
                    + "(4) Add a new budget \n"
                    + "(5) Change an existed budget \n"
                    + "(6) See your expenses in a day \n"
                    + "(7) Check whether exceed your budget \n"
                    + "(8) Save and quit recorder";


    // EFFECTS: runs the receipt recorder application
    public ReceiptRecorderApp() {
        runReceiptRecorder();
    }

    // REQUIRES: int should not be beginning with 0 and int > 0
    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initial() {
        receiptRecorder = new ReceiptRecorder();
        budget = 0.00;
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

            if (operation.equals("8")) {
                keepRunning = false;
            } else {
                processOperation(operation);
            }
        }
        System.out.println("\nHave a nice day, goodbye!");
    }

    // EFFECTS: go to different operations based on user's choice
    private void processOperation(String operation) {
        if (operation.equals("1")) {
            createNewReceipt();
        } else if (operation.equals("2")) {
            changeReceipt();
        } else if (operation.equals("3")) {
            deleteReceipt();
        } else if (operation.equals("4")) {
            System.out.println("Enter the budget amount");
            budget = input.nextDouble();
        } else if (operation.equals("5")) {
            changeBudget();
        } else if (operation.equals("6")) {
            checkExpenses();
        } else if (operation.equals("7")) {
            checkBudget();
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new receipt
    private void createNewReceipt() {
        System.out.println("Enter your new receipt in $: ");
        double amount = input.nextDouble();
        System.out.println("What did you buy: ");
        String item = input.nextLine();
        receiptRecorder.addReceipt(amount, item);
    }

    // MODIFIES: this
    // EFFECTS: change an existed receipt
    private void changeReceipt() {
        System.out.println("Enter what did you buy: ");
        String item = input.nextLine();
        receipt = receiptRecorder.findReceipt(item);

        System.out.println("What do you want to change? amount/item/nothing");
        while (!input.nextLine().equals("nothing")) {
            makeChangeForReceipt();
        }
    }

    // MODIFIES: this
    // EFFECTS: delete an existed receipt
    private void deleteReceipt() {
        System.out.println("Enter the item of receipt you want to delete");
        String item = input.nextLine();
        receiptRecorder.findReceipt(item);
        receiptRecorder.removeReceipt(item);
    }

    // MODIFIES: this
    // EFFECTS: change the budget
    private void changeBudget() {
        double currentBudget = receiptRecorder.getBudget();
        System.out.println("Your current amount of budget is " + currentBudget);
        System.out.println("Enter new amount of budget want to change: ");
        double newAmount = input.nextDouble();
        receiptRecorder.setBudget(newAmount);
    }

    // MODIFIES: this
    // EFFECTS: output the total amount of spending in a given time period
    private void checkExpenses() {
        double totalAmount = receiptRecorder.checkExpenses();
        System.out.println("Your total expenses amount is " + totalAmount + " dollars");
    }

    // MODIFIES: this
    // EFFECTS: check whether spending exceeds setting budget
    private void checkBudget() {
        receiptRecorder.checkBudget();
        if (receiptRecorder.checkBudget()) {
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
            receipt.changeAmount(newAmount);
        } else if (input.nextLine().equals("item")) {
            String newItem = input.nextLine();
            receipt.changeItem(newItem);
        }
    }
}
