package ui;

import model.Receipt;
import model.ReceiptRecorder;
import model.exceptions.ReceiptDoesNotExistException;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

// Receipt recorder application
public class ReceiptRecorderApp {

    private ReceiptRecorder receiptRecorder;
    private static final String JSON_STORE = "./data/receiptRecorder.json";
    private Receipt receipt;
    private double budget;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String selectYourOperation =
            "You want to: \n"
                    + "(1) Add a new receipt \n"
                    + "(2) Change an existed receipt \n"
                    + "(3) Delete an existed receipt \n"
                    + "(4) Add a new budget (default: 0.0) \n"
                    + "(5) See your total expenses \n"
                    + "(6) Check whether exceed your budget \n"
                    + "(7) Save and quit recorder";


    // EFFECTS: runs the receipt recorder application
    public ReceiptRecorderApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        runReceiptRecorder();
    }

    // REQUIRES: int should not be beginning with 0 and int > 0
    // MODIFIES: this
    // EFFECTS: initializes fields
    private void initial() {
        receiptRecorder = new ReceiptRecorder("My receipt recorder");
        budget = 0.00;
    }

    // MODIFIES: this
    // EFFECTS: processes user's command or quit app
    private void runReceiptRecorder() {
        boolean keepRunning = true;
        String operation;

        initial();
        loadReceiptRecorder();

        while (keepRunning) {
            System.out.println(selectYourOperation);
            operation = input.nextLine();

            if (operation.equals("7")) {
                saveReceiptRecorder();
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
                changeBudget();
                break;
            case "5":
                checkExpenses();
                break;
            case "6":
                checkBudget();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new receipt
    private void createNewReceipt() {
        System.out.println("Enter your new receipt in $: ");
        double amount = Double.parseDouble(input.nextLine());
        System.out.println("Enter the name of item you bought: ");
        String item = input.nextLine();
        receiptRecorder.addReceipt(amount, item);
        System.out.println(receiptRecorder.showAllReceipt());
    }

    // MODIFIES: this
    // EFFECTS: change an existed receipt
    @SuppressWarnings("methodlength")
    private void changeReceipt() {
        System.out.println("Enter the name of item you bought: ");
        String item = input.nextLine();
        try {
            receipt = receiptRecorder.findReceipt(item);

            System.out.println("What do you want to change? amount/item/nothing");
            String choice = input.nextLine();
            if (choice.equals("amount")) {
                System.out.println("Enter the amount: ");
                double newAmount = Double.parseDouble(input.nextLine());
                receipt.changeAmount(newAmount);
                System.out.println("Change the amount to " + newAmount);
            } else if (choice.equals("item")) {
                System.out.println("Enter the item: ");
                String newItem = input.nextLine();
                receipt.changeItem(newItem);
                System.out.println("Change the item to " + newItem);
            } else {
                System.out.println("You have nothing to change");
            }
        } catch (ReceiptDoesNotExistException e) {
            System.out.println("Sorry, the receipt doesn't exist");
        } finally {
            System.out.println(receiptRecorder.showAllReceipt());
        }
    }

    // MODIFIES: this
    // EFFECTS: delete an existed receipt
    private void deleteReceipt() {
        System.out.println("Enter the item of receipt you want to delete");
        String item = input.nextLine();
        try {
            receiptRecorder.findReceipt(item);
            receiptRecorder.removeReceipt(item);
        } catch (ReceiptDoesNotExistException e) {
            System.out.println("Sorry, the receipt doesn't exist");
        } finally {
            System.out.println(receiptRecorder.showAllReceipt());
        }
    }

    // MODIFIES: this
    // EFFECTS: change the budget
    private void changeBudget() {
        double currentBudget = receiptRecorder.getBudget();
        System.out.println("Your current amount of budget is " + currentBudget);
        System.out.println("Enter new amount of budget want to change: ");
        double newAmount = Double.parseDouble(input.nextLine());
        receiptRecorder.setBudget(newAmount);
        System.out.println(receiptRecorder.showAllReceipt());
    }

    // MODIFIES: this
    // EFFECTS: output the total amount of spending in a given time period
    private void checkExpenses() {
        double totalAmount = receiptRecorder.checkExpenses();
        System.out.println(receiptRecorder.showAllReceipt());
        System.out.println("Your total expenses amount is " + totalAmount + " dollars");
    }

    // MODIFIES: this
    // EFFECTS: check whether spending exceeds setting budget
    private void checkBudget() {
        System.out.println(receiptRecorder.showAllReceipt());
        if (receiptRecorder.checkBudget()) {
            System.out.println("Be careful, your spending exceeds your budget \n");
        } else {
            System.out.println("Great, your spending doesn't exceed your budget \n");
        }
    }

    // citation URL: https://github.com/stleary/JSON-java.git
    // EFFECTS: saves the receiptRecorder to file
    private void saveReceiptRecorder() {
        try {
            jsonWriter.open();
            jsonWriter.write(receiptRecorder);
            jsonWriter.close();
            System.out.println("Saved " + receiptRecorder.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // citation URL: https://github.com/stleary/JSON-java.git
    // MODIFIES: this
    // EFFECTS: loads receiptRecorder from file
    private void loadReceiptRecorder() {
        try {
            receiptRecorder = jsonReader.read();
            System.out.println("Loaded " + receiptRecorder.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
