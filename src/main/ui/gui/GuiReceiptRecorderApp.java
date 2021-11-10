package ui.gui;

import model.ReceiptRecorder;
import persistence.JsonReader;
import ui.gui.ReceiptList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiReceiptRecorderApp implements ActionListener {
    private static final int frameHeight = 300;
    private static final int frameWidth = 475;
    private JFrame frame;
    private ReceiptList receiptList;
    private ReceiptRecorder receiptRecorder;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/receiptRecorder.json";

    public GuiReceiptRecorderApp() {

        frame = new JFrame("Receipt Recorder");
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(new BorderLayout());

        jsonReader = new JsonReader(JSON_STORE);
        loadReceiptRecorder();

        JButton button1 = new JButton("Start a new receipt");
        JButton button2 = new JButton("Load previous receipt");
        JButton button3 = new JButton("Save and quit app");

        frame.add(button1, BorderLayout.WEST);
        button1.setEnabled(true);
        button1.addActionListener(this);

        frame.add(button2, BorderLayout.CENTER);
        button2.setEnabled(true);
        button2.addActionListener(this);

        frame.add(button3, BorderLayout.EAST);
        button3.setEnabled(true);
        button3.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Start a new receipt")) {
            receiptList = new ReceiptList(new ReceiptRecorder("My receipt recorder"));
            JOptionPane.showMessageDialog(
                    null, "Your receipt status has been reset");
        } else if (action.equals("Load previous receipt")) {
            receiptList = new ReceiptList(receiptRecorder);
            JOptionPane.showMessageDialog(
                    null, "Your receipt record has been loaded");
        } else if (action.equals("Save and quit app")) {
            receiptList.dispose();
            frame.dispose();
        }
    }
}
