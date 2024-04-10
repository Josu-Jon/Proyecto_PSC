package com.psc06.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import server.jdo.UserStory;

public class Interface extends JFrame {
    private JTable usTable;
    private JButton createButton;
    private JButton deleteButton;
    private List<UserStory> userStories;

    public Interface() {
        setTitle("Product Backlog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Título");
        model.addColumn("Prioridad");
        model.addColumn("Estimación");
        model.addColumn("Eliminar");

        usTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(usTable);
        add(scrollPane, BorderLayout.CENTER);

        createButton = new JButton("Crear User Story");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserStoryDialog();
            }
        });


        deleteButton = new JButton("Eliminar User Story");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = usTable.getSelectedRow();
                if (selectedRow != -1) {
                    ((DefaultTableModel) usTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione una User Story para eliminar.", "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // prueba
        userStories = new ArrayList<>();
        userStories.add(new UserStory("US1", 1, 5));
        userStories.add(new UserStory("US2", 2, 8));
        userStories.add(new UserStory("US3", 3, 3));
        updateUserStoriesTable();

        setVisible(true);
    }

    private void createUserStoryDialog() {
        JFrame dialog = new JFrame("Crear Nueva User Story");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Título:");
        JTextField titleField = new JTextField();
        JLabel priorityLabel = new JLabel("Prioridad:");
        JTextField priorityField = new JTextField();
        JLabel estimationLabel = new JLabel("Estimación:");
        JTextField estimationField = new JTextField();

        JButton createButton = new JButton("Crear");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                int priority = Integer.parseInt(priorityField.getText());
                int estimation = Integer.parseInt(estimationField.getText());

                userStories.add(new UserStory(title, priority, estimation));
                updateUserStoriesTable();

                dialog.dispose();
            }
        });

        dialog.add(titleLabel);
        dialog.add(titleField);
        dialog.add(priorityLabel);
        dialog.add(priorityField);
        dialog.add(estimationLabel);
        dialog.add(estimationField);
        dialog.add(new JLabel()); 
        dialog.add(createButton);

        dialog.setVisible(true);
    }

    private void updateUserStoriesTable() {
        DefaultTableModel model = (DefaultTableModel) usTable.getModel();
        model.setRowCount(0); 
        for (UserStory us : userStories) {
            model.addRow(new Object[]{us.getTitle(), us.getPriority(), us.getEstimation(), "Eliminar"});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface();
            }
        });
    }

}


