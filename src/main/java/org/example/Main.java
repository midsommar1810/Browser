package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends JFrame {
    private JTextField urlField;
    private JEditorPane displayPane;
    public Main() {
        super("Simple Java Browser");

        // Set up URL input field
        urlField = new JTextField("https://");
        urlField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPage(urlField.getText());
            }
        });

        // Set up display pane for HTML
        displayPane = new JEditorPane();
        displayPane.setEditable(false);         // Display only

        JScrollPane scrollPane = new JScrollPane(displayPane);

        add(urlField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to load page in the display pane
    private void loadPage(String url) {
        try {
            displayPane.setPage(url);  // Load and display page
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading page", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
        System.out.println("Hello world!");

    }
}