package ui.gui;

import model.Receipt;
import model.ReceiptRecorder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReceiptList implements ActionListener {
    private JFrame receiptFrame;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel label1;
    private JLabel label2;
    private Box buttonBox;

    private ReceiptRecorder receiptRecorder;
    private int frameWidth = 800;
    private int frameHeight = 1400;

    public ReceiptList(ReceiptRecorder receiptRecorder) {
        receiptFrame = new JFrame();
        this.receiptRecorder = receiptRecorder;
        this.buttonBox = Box.createVerticalBox();
        generateLabel();
        receiptFrame.setSize(frameWidth, frameHeight);
        final String[] columnLabels = new String[]{
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
        receiptFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 20,10));
        receiptFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        receiptFrame.pack();
        receiptFrame.setVisible(true);
    }

    private void generateLabel() {
        double budget = receiptRecorder.getBudget();
        label1 = new JLabel("\nYour total expense is " + budget);
        if (receiptRecorder.checkBudget()) {
            label2 = new JLabel("\nBe careful, your spending exceeds your budget");
        } else {
            label2 = new JLabel("\nGreat, your spending doesn't exceed your budget");
        }
    }

    private void setButtons() {
        JButton addItemButton = new JButton(("Add a new receipt"));
        buttonBox.add(addItemButton);
        addItemButton.setActionCommand("Add a new receipt");
        addItemButton.addActionListener(this);

        JButton changeItemButton = new JButton(("Change a receipt"));
        buttonBox.add(changeItemButton);
        addItemButton.setActionCommand("Change a receipt");
        addItemButton.addActionListener(this);

        JButton deleteItemButton = new JButton(("Delete a receipt"));
        buttonBox.add(deleteItemButton);
        addItemButton.setActionCommand("Delete a receiptt");
        addItemButton.addActionListener(this);

        buttonBox.add(label1);
        buttonBox.add(label2);

    }

    private void generateTableRows() {
        for (int i = 0; i < receiptRecorder.getReceipts().size(); i++) {
            Receipt receipt = receiptRecorder.getReceipts().get(i);
            Object[] tableRow = new Object[] {
                    i, // index column
                    receipt.getItem(), // item column
                    receipt.getAmount() // amount column
            };
            tableModel.addRow(tableRow);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void dispose() {
        receiptFrame.dispose();
    }
}
