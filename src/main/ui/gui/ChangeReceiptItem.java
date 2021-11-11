package ui.gui;

import model.Receipt;
import model.ReceiptRecorder;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class ChangeReceiptItem extends JFrame implements ActionListener {
    private JPanel panel;
    private JTextField itemName;
    private JTextField itemPrice;
    private ReceiptTable receiptTable;
    private ReceiptRecorder receiptRecorder;
    private Receipt receipt;
    private String temp;
    private static final String JSON_STORE = "./data/receiptRecorder.json";
    private JsonWriter jsonWriter;

    public ChangeReceiptItem(ReceiptTable receiptTable,
                             ReceiptRecorder receiptRecorder, int index) {
        super("Change receipt item name");
        panel = new JPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        this.setSize(600,400);
        this.receiptTable = receiptTable;
        this.receiptRecorder = receiptRecorder;
        this.receipt = receiptRecorder.findReceiptByIndex(index);
        this.changeReceipt();
        this.add(panel);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void changeReceipt() {
        JLabel itemNameLabel = new JLabel("Enter new item: ");
        itemNameLabel.setBounds(100, 50, 400, 20);
        panel.add(itemNameLabel);

        itemName = new JTextField(20);
        itemName.setBounds(100, 100, 400, 20);
        panel.add(itemName);

        JLabel amountLabel = new JLabel("Enter the new price of the item: ");
        amountLabel.setBounds(100, 150, 400, 20);
        panel.add(amountLabel);

        itemPrice = new JTextField(20);
        itemPrice.setBounds(100, 200,400,20);
        panel.add(itemPrice);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(250,250,100,20);
        panel.add(confirm);
        confirm.setActionCommand("Confirm");
        confirm.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Confirm")) {
            String item = itemName.getText();
            double amount = Double.parseDouble(itemPrice.getText());
            receipt.changeItem(item);
            receipt.changeAmount(amount);

            receiptTable.save();
            receiptTable.dispose();
            new ReceiptTable(receiptRecorder);
            dispose();
        }
    }
}
