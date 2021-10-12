package ui;

import model.Budget;
import model.Receipt;
import model.ReceiptRecorder;

import java.util.Scanner;

// Receipt recorder application
public class ReceiptRecorderApp {

    private ReceiptRecorder receiptRecorder;
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

    // runs the receipt recorder application
    public ReceiptRecorderApp() {
        runReceiptRecorder();
    }

    private void runReceiptRecorder() {
        boolean keepGoing = true;
        String operation = null;

        while (keepGoing) {
            System.out.println(selectYourOperation);
            operation = input.nextLine();

            if (operation.equals("9")) {
                keepGoing = false;
            } else {
                processOperation(operation);
            }
        }
        System.out.println("\nHave a nice day, goodbye!");
    }

    private void processOperation(String operation) {
        if (operation == "1") {
            createNewReceipt();
        } else if (operation == "2") {
            changeReceipt();
        } else if (operation == "3") {
            deleteReceipt();
        } else if (operation == "4") {
            addBudget();
        } else if (operation == "5") {
            changeBudget();
        } else if (operation == "6") {
            deleteBudget();
        } else if (operation == "7") {
            checkExpenses();
        } else if (operation == "8") {
            checkBudget();
        }
    }

    private void createNewReceipt() {
        System.out.println("Enter your new receipt in $: ");
        double amount = input.nextDouble();
        System.out.println("What did you buy: ");
        String item = input.nextLine();
        System.out.println("What is the date(MM/DD/YYYY): ");
        String date = input.nextLine();
        receiptRecorder.addReceipt(amount, item, date);
    }

    private void changeReceipt() {
        System.out.println("Enter your receipt date(MM/DD/YYYY): ");
        String date = input.nextLine();
        System.out.println("Enter did you buy: ");
        String item = input.nextLine();
        receiptRecorder.findReceipt(date, item);

        System.out.println("What do you want to change? amount/item/date");
        if (input.nextLine() == "amount") {
            double newAmount = input.nextDouble();
            receiptRecorder.changeAmount(newAmount);
        } else if (input.nextLine() == "item") {
            String newItem = input.nextLine();
            receiptRecorder.changeItem(newItem);
        } else if (input.nextLine() == "date") {
            String newDate = input.nextLine();
            receiptRecorder.changeDate(newDate);
        }
    }

    private void deleteReceipt() {
    }

    private void addBudget() {
    }

    private void changeBudget() {
    }

    private void deleteBudget() {
    }

    private void checkExpenses() {
    }

    private void checkBudget() {
    }

}
