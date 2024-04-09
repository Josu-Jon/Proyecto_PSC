package com.psc06.Interfaces;

import javax.swing.*;
import java.awt.*;

public class USInterface extends JFrame {
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JTextField titleTextField;
    private JTextArea descriptionTextArea;
    private JButton createButton;

    public USInterface() {
        setTitle("Crear Historia de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(3, 2));

        titleLabel = new JLabel("Título:");
        add(titleLabel);

        titleTextField = new JTextField();
        add(titleTextField);

        descriptionLabel = new JLabel("Descripción:");
        add(descriptionLabel);

        descriptionTextArea = new JTextArea();
        add(new JScrollPane(descriptionTextArea));

        createButton = new JButton("Crear");
        add(createButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(USInterface::new);
    }
}

