package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiReceiptRecorderApp implements ActionListener {
    private static final int frameHeight = 300;
    private static final int frameWidth = 475;

    public GuiReceiptRecorderApp() {
        JFrame frame = new JFrame("Receipt Recorder");
        frame.setSize(frameWidth, frameHeight);
        frame.setLayout(new BorderLayout());
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
        if (action.equals("Start a new receipt")) {
            JOptionPane.showMessageDialog(
                    null, "Your receipt status has been reset");
        } else if (action.equals("Load previous receipt")) {
            //stub
        } else if (action.equals("Save and quit app")) {
            //stub
        }
    }
}
