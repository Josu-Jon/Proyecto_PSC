package com.psc06.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.psc06.server.UserStory;
import java.util.List;
public class USInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
	private JLabel priorityLabel; 
    private JLabel descriptionLabel;
    private JTextField titleTextField;
    private JTextField priorityTextField;
    private JTextArea descriptionTextArea;
    private JButton createButton;

    private static List<UserStory> userStories = new ArrayList<>();
    public USInterface() {
        setTitle("Crear Historia de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));

        JLabel headerLabel = new JLabel("<html><font size='+2'><b>Crear User Story</b></font></html>");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(headerLabel);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        
        titleLabel = new JLabel("Título:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(titleLabel);

        titleTextField = new JTextField();
        inputPanel.add(titleTextField);


        descriptionLabel = new JLabel("Descripción:");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(descriptionLabel);

        descriptionTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        inputPanel.add(scrollPane);

        centerPanel.add(inputPanel);

        createButton = new JButton("Crear");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText().trim();
                String description = descriptionTextArea.getText().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    
                    JOptionPane.showMessageDialog(USInterface.this, "Historia de usuario creada: \n\nTítulo: " + title + "\nDescripción: " + description);
                } else {
                    JOptionPane.showMessageDialog(USInterface.this, "Por favor, complete todos los campos para crear la historia de usuario.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(centerPanel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(USInterface::new);
    }
}

