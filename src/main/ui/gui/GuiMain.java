package ui.gui;

import java.io.FileNotFoundException;

// run ReceiptRecorderApp
public class GuiMain {
    public static void main(String[] args) {
        try {
            new GuiReceiptRecorderApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
