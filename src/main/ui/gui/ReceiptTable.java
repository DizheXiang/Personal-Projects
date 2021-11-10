package ui.gui;

import model.Receipt;
import model.ReceiptRecorder;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ReceiptTable implements ActionListener {
    private JFrame receiptFrame;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel label1;
    private JLabel label2;
    private Box buttonBox;

    private static final String JSON_STORE = "./data/receiptRecorder.json";
    private JsonWriter jsonWriter;

    private ReceiptRecorder receiptRecorder;
    private double budget;
    private int frameWidth = 800;
    private int frameHeight = 1400;
    private static String ADD_RECEIPT = "Add a new receipt";
    private static String CHANGE_RECEIPT_ITEM = "Change item of the receipt (click on the row)";
    private static String CHANGE_RECEIPT_AMOUNT = "Change price of the receipt (click on the row)";
    private static String DELETE_RECEIPT = "Delete a receipt (click on the row)";
    private static String CHANGE_BUDGET = "Change the budget";


    public ReceiptTable(ReceiptRecorder receiptRecorder) {
        jsonWriter = new JsonWriter(JSON_STORE);
        receiptFrame = new JFrame();
        this.receiptRecorder = receiptRecorder;
        this.budget = receiptRecorder.getBudget();
        this.buttonBox = Box.createVerticalBox();
        generateLabel();
        receiptFrame.setSize(frameWidth, frameHeight);
        final String[] columnLabels = new String[]{
                "Index",
                "Item",
                "Amount"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableModel);
        this.generateTableRows();

        receiptFrame.add(new JScrollPane(table));
        this.setButtons();
        receiptFrame.add(buttonBox);

        receiptFrame.setTitle("Receipt Record");
        receiptFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        receiptFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        receiptFrame.pack();
        receiptFrame.setVisible(true);
    }

    private void generateLabel() {
        label1 = new JLabel("\nYour total expense is " + budget);
        if (receiptRecorder.checkBudget()) {
            label2 = new JLabel("\nBe careful, your spending exceeds your budget");
        } else {
            label2 = new JLabel("\nGreat, your spending doesn't exceed your budget");
        }
    }

    private void setButtons() {
        JButton addItemButton = new JButton(ADD_RECEIPT);
        buttonBox.add(addItemButton);
        addItemButton.addActionListener(this);

        JButton changeItemButton = new JButton(CHANGE_RECEIPT_ITEM);
        buttonBox.add(changeItemButton);
        addItemButton.addActionListener(this);

        JButton changeAmountButton = new JButton(CHANGE_RECEIPT_AMOUNT);
        buttonBox.add(changeAmountButton);
        addItemButton.addActionListener(this);

        JButton deleteItemButton = new JButton(DELETE_RECEIPT);
        buttonBox.add(deleteItemButton);
        addItemButton.addActionListener(this);

        JButton changeBudgetButton = new JButton(CHANGE_BUDGET);
        buttonBox.add(changeBudgetButton);
        addItemButton.addActionListener(this);

        buttonBox.add(label1);
        buttonBox.add(label2);

    }

    private void generateTableRows() {
        for (int i = 0; i < receiptRecorder.getReceipts().size(); i++) {
            Receipt receipt = receiptRecorder.getReceipts().get(i);
            Object[] tableRow = new Object[]{
                    i, // index column
                    receipt.getItem(), // item column
                    receipt.getAmount() // amount column
            };
            tableModel.addRow(tableRow);
        }
    }

    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if (action.equals(ADD_RECEIPT)) {
            new AddReceipt(this, receiptRecorder);

        } else if (action.equals(CHANGE_RECEIPT_ITEM)) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please select a receipt to delete");
                return;
            }
            Receipt receipt = receiptRecorder.findReceiptByIndex(selectedRowIndex);
            receipt.changeItem((String) e.getSource());
            table.setValueAt(e.getSource(), selectedRowIndex, 2);
            save();

        } else if (action.equals(CHANGE_RECEIPT_AMOUNT)) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null,
                        "Please select a receipt to delete");
                return;
            }
            Receipt receipt = receiptRecorder.findReceiptByIndex(selectedRowIndex);
            receipt.changeAmount(Double.parseDouble((String) e.getSource()));
            table.setValueAt(e.getSource(), selectedRowIndex, 3);
            save();

        } else if (action.equals(DELETE_RECEIPT)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int[] selectedRows = table.getSelectedRows();
            for (int i = selectedRows[0]; i < selectedRows.length; i++) {
                model.removeRow(selectedRows[0]);
            }
            table.setModel(model);

        } else if (action.equals(CHANGE_BUDGET)) {
            //stub
        }
    }

    public void dispose() {
        receiptFrame.dispose();
    }

    // EFFECTS: saves the receiptRecorder to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(receiptRecorder);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Data saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to save data");
        }
    }
}
