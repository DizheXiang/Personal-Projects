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

public class ReceiptTable extends JFrame implements ActionListener {
    private final DefaultTableModel tableModel;
    private final JTable table;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private final Box buttonBox;

    private static final String JSON_STORE = "./data/receiptRecorder.json";
    private final JsonWriter jsonWriter;

    private final ReceiptRecorder receiptRecorder;
    private double budget;
    private static final String ADD_RECEIPT = "Add a new receipt";
    private static final String CHANGE_RECEIPT = "Change the receipt (click on the row)";
    private static final String DELETE_RECEIPT = "Delete a receipt (click on the row)";
    private static final String CHANGE_BUDGET = "Change the budget";


    @SuppressWarnings("methodlength")
    public ReceiptTable(ReceiptRecorder receiptRecorder) {
        super("Receipt Record");
        jsonWriter = new JsonWriter(JSON_STORE);
        this.receiptRecorder = receiptRecorder;
        this.budget = receiptRecorder.getBudget();
        this.buttonBox = Box.createVerticalBox();
        generateLabel();
        int frameWidth = 800;
        int frameHeight = 1400;
        setSize(frameWidth, frameHeight);
        final String[] columnLabels = new String[]{
                "Index",
                "Item",
                "Amount"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableModel);
        this.generateTableRows();

        add(new JScrollPane(table));
        this.setButtons();
        add(buttonBox);

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void generateLabel() {
        label1 = new JLabel("\nYour total expense is " + receiptRecorder.checkExpenses());
        if (receiptRecorder.checkBudget()) {
            label2 = new JLabel("\nBe careful, your spending exceeds your budget");
        } else {
            label2 = new JLabel("\nGreat, your spending doesn't exceed your budget");
        }
        label3 = new JLabel("\nYour budget is " + receiptRecorder.getBudget());
    }

    private void setButtons() {
        JButton addItemButton = new JButton(ADD_RECEIPT);
        buttonBox.add(addItemButton);
        addItemButton.addActionListener(this);

        JButton changeItemButton = new JButton(CHANGE_RECEIPT);
        buttonBox.add(changeItemButton);
        changeItemButton.addActionListener(this);

        JButton deleteItemButton = new JButton(DELETE_RECEIPT);
        buttonBox.add(deleteItemButton);
        deleteItemButton.addActionListener(this);

        JButton changeBudgetButton = new JButton(CHANGE_BUDGET);
        buttonBox.add(changeBudgetButton);
        changeBudgetButton.addActionListener(this);

        buttonBox.add(label1);
        buttonBox.add(label3);
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

        switch (action) {
            case ADD_RECEIPT:
                new AddReceipt(this, receiptRecorder);
                break;
            case CHANGE_RECEIPT: {
                int selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Please select a receipt to delete");
                    return;
                }
                new ChangeReceiptItem(this, receiptRecorder, selectedRowIndex);
                table.setValueAt((Object)
                                receiptRecorder.findReceiptByIndex(selectedRowIndex).getItem(),
                        selectedRowIndex, 2);
                table.setValueAt((Object)
                                receiptRecorder.findReceiptByIndex(selectedRowIndex).getAmount(),
                        selectedRowIndex, 3);
                save();
                break;
            }
            case DELETE_RECEIPT: {
                int selectedRowIndex = table.getSelectedRow();
                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null,
                            "Please select a receipt to delete");
                }
                receiptRecorder.removeReceiptByIndex(selectedRowIndex);
                tableModel.removeRow(selectedRowIndex);
                save();
                break;
            }
            case CHANGE_BUDGET:
                new EditBudget(this, receiptRecorder);
                break;
        }
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
