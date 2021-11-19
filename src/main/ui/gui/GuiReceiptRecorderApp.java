package ui.gui;

import model.ReceiptRecorder;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.apple.eio.FileManager.getResource;
import static com.sun.tools.doclint.Entity.image;

public class GuiReceiptRecorderApp extends JFrame implements ActionListener {
    private static final int frameHeight = 300;
    private static final int frameWidth = 475;
    private ReceiptTable receiptTable;
    private ReceiptRecorder receiptRecorder;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/receiptRecorder.json";

    @SuppressWarnings("methodlength")
    public GuiReceiptRecorderApp() {
        super("Receipt Recorder");
        setSize(frameWidth, frameHeight);
        setLayout(new BorderLayout());
        jsonReader = new JsonReader(JSON_STORE);
        receiptRecorder = new ReceiptRecorder("My receipt recorder");

        load();

        JButton button1 = new JButton("Start a new receipt");
        JButton button2 = new JButton("Load previous receipt");
        JButton button3 = new JButton("Save and quit app");

        add(button1, BorderLayout.WEST);
        button1.setEnabled(true);
        button1.addActionListener(this);
        add(button2, BorderLayout.CENTER);
        button2.setEnabled(true);
        button2.addActionListener(this);
        add(button3, BorderLayout.EAST);
        button3.setEnabled(true);
        button3.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
                JOptionPane.showMessageDialog(
                        null, "Goodbye, have a nice day ✧*٩(ˊᗜˋ*)و✧*");
                receiptTable.dispose();
                dispose();
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads receiptRecorder from file
    public void load() {
        try {
            this.receiptRecorder = jsonReader.read();
            Image picture = ImageIO.read(getClass().getResource("./image/project_image.png"));
            ImageIcon imageIcon = new ImageIcon(picture);
            JOptionPane.showMessageDialog(null, imageIcon);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to load data");
        }
    }
}
