package com.psc06.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class USInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titleLabel;
    private JLabel priorityLabel;
    private JLabel estimationLabel; 
    private JTextField titleTextField;
    private JTextField priorityTextField;
    private JTextField estimationTextField; 
    private JButton createButton;

    private static List<UserStory> userStories = new ArrayList<>();

    public USInterface() {
        setTitle("Crear Historia de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); 
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 1)); 

        JLabel headerLabel = new JLabel("<html><font size='+2'><b>Crear User Story</b></font></html>");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(headerLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2)); 

        titleLabel = new JLabel("Título:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(titleLabel);

        titleTextField = new JTextField();
        inputPanel.add(titleTextField);

        priorityLabel = new JLabel("Prioridad:");
        priorityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(priorityLabel);

        priorityTextField = new JTextField();
        inputPanel.add(priorityTextField);

        estimationLabel = new JLabel("Estimación:"); 
        estimationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputPanel.add(estimationLabel);

        estimationTextField = new JTextField(); 
        inputPanel.add(estimationTextField);

        centerPanel.add(inputPanel);

        createButton = new JButton("Crear");
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText().trim();
                int priority, estimation;

                try {
                    priority = Integer.parseInt(priorityTextField.getText().trim());
                    estimation = Integer.parseInt(estimationTextField.getText().trim()); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(USInterface.this, "La prioridad y la estimación deben ser números enteros.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                if (!title.isEmpty()) {
                    UserStory newUserStory = new UserStory(userStories.size() + 1, title, priority, estimation); 
                    userStories.add(newUserStory);

                    JOptionPane.showMessageDialog(USInterface.this, "Historia de usuario creada: \n\nTítulo: " + title + "\nPrioridad: " + priority + "\nEstimación: " + estimation);

                    titleTextField.setText("");
                    priorityTextField.setText("");
                    estimationTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(USInterface.this, "Por favor, complete todos los campos para crear la historia de usuario.", "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        centerPanel.add(createButton);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(USInterface::new);
    }
}
