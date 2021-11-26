package ui.gui;

import model.ReceiptRecorder;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// a new frame to edit budget
public class EditBudget extends JFrame implements ActionListener {
    private JPanel jp;
    private JTextField newBudget;
    private ReceiptRecorder receiptRecorder;
    private ReceiptTable receiptTable;
    private static final String JSON_STORE = "./data/receiptRecorder.json";
    private JsonWriter jsonWriter;


    public EditBudget(ReceiptTable receiptTable, ReceiptRecorder receiptRecorder) {
        super("Budget");
        jsonWriter = new JsonWriter(JSON_STORE);
        jp = new JPanel();
        this.receiptRecorder = receiptRecorder;
        this.receiptTable = receiptTable;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        add(jp);
        setVisible(true);

        setLabels();
    }

    // EFFECTS: produce the text field to enter the new budget
    private void setLabels() {
        JLabel budgetLabel = new JLabel("Enter new budget: ");
        budgetLabel.setBounds(50, 20, 200, 20);
        jp.add(budgetLabel);

        newBudget = new JTextField(20);
        newBudget.setBounds(50, 40, 200, 20);
        jp.add(newBudget);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(250, 250, 100, 20);
        jp.add(confirm);
        confirm.setActionCommand("Confirm");
        confirm.addActionListener(this);
    }

    @Override
    //EFFECTS: if click the "Confirm" button then quit the current frame
    // and add the receipt to the receipt tables
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Confirm")) {
            double  budget = Double.parseDouble(newBudget.getText());
            receiptRecorder.setBudget(budget);

            receiptTable.save();
            receiptTable.dispose();
            new ReceiptTable(receiptRecorder);
            dispose();
        }
    }
}
