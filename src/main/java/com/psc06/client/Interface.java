package com.psc06.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.psc06.server.jdo.UserStory;

public class Interface extends JFrame {
    private JTable usTable;
    private JButton createButton;
    private JButton deleteButton;
    private JButton editButton;
    private List<UserStoryData> userStories;

    public Interface() {
        setTitle("Product Backlog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Título");
        model.addColumn("Prioridad");
        model.addColumn("Estimación");
        model.addColumn("Eliminar");

        usTable = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
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
                    int id = (int) usTable.getValueAt(selectedRow, 0); 
                    ClientServer.deleteUserStory(id);
                    ((DefaultTableModel) usTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione una User Story para eliminar.", "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    editButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedRow = usTable.getSelectedRow();
        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(null, "Por favor, selecciona una User Story para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        usTable.setEditable(true);
        int id = (int) usTable.getValueAt(selectedRow, 0); 
        String userStory = (String) usTable.getValueAt(selectedRow, 1); 
        int estimation = (int) usTable.getValueAt(selectedRow, 2); 
        int pbPriority = (int) usTable.getValueAt(selectedRow, 3); 


        ClientServer.modifyUserStory(id, userStory, estimation, pbPriority);
    }
});

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(editButton);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        userStories = ClientServer.getAllUserStorys();
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
                ClientServer.registerUserStory(id, title, priority, estimation);
                dialog.dispose();
            }
        });
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });


        dialog.add(titleLabel);
        dialog.add(titleField);
        dialog.add(priorityLabel);
        dialog.add(priorityField);
        dialog.add(estimationLabel);
        dialog.add(estimationField);
        dialog.add(cancelButton);
        dialog.add(createButton);

        dialog.setVisible(true);
    }

    private void updateUserStoriesTable() {
        DefaultTableModel model = (DefaultTableModel) usTable.getModel();
        model.setRowCount(0);
        for (UserStoryData usd : userStories) {
            model.addRow(new Object[]{usd.getId(), usd.getTitle(), usd.getPriority(), usd.getEstimation(), "Eliminar"});
        }
    }

}
