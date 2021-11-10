package ui.gui;

import model.ReceiptRecorder;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiReceiptRecorderApp implements ActionListener {
    private static final int frameHeight = 300;
    private static final int frameWidth = 475;
    private JFrame frame;
    private ReceiptTable receiptTable;
    private ReceiptRecorder receiptRecorder;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/receiptRecorder.json";

    @SuppressWarnings("methodlength")
    public GuiReceiptRecorderApp() {
        frame = new JFrame("Receipt Recorder");
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(new BorderLayout());
        jsonReader = new JsonReader(JSON_STORE);
        receiptRecorder = new ReceiptRecorder("My receipt recorder");

        load();

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Start a new receipt":
                receiptTable = new ReceiptTable(
                        this.receiptRecorder = new ReceiptRecorder("My receipt recorder"));
                JOptionPane.showMessageDialog(
                        null, "Your receipt status has been reset");
                break;
            case "Load previous receipt":
                receiptTable = new ReceiptTable(receiptRecorder);
                JOptionPane.showMessageDialog(
                        null, "Your receipt record has been loaded");
                break;
            case "Save and quit app":
                receiptTable.dispose();
                frame.dispose();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads receiptRecorder from file
    public void load() {
        try {
            this.receiptRecorder = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Data loaded");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to load data");
        }
    }

}
