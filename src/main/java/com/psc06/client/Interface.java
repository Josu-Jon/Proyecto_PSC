package com.psc06.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.psc06.pojo.UserStoryData;

public class Interface extends JPanel {
    private JTabbedPane tabbedPane;
    private JTable usTable;
    private JTable sprintTable;
    private JButton createUserStoryButton;
    private JButton deleteUserStoryButton;
    private JButton createSprintButton;
    private JButton deleteSprintButton;
    private JButton editUserStoryButton;

    private List<UserStoryData> userStories;
    //private List<SprintData> sprints;

    //private ClientServer clientServer;

    public Interface(ClientServer clientServerAux) {
        // clientServer = clientServerAux;
        // userStories = clientServer.getAllUserStories();
        
        // Datos de prueba
        userStories = new ArrayList<>();
        userStories.add(new UserStoryData(1, "Crear cliente y servidor", 5, 2));
        userStories.add(new UserStoryData(2, "Crear pom", 2, 7));

        tabbedPane = new JTabbedPane();

        // Panel para User Stories
        JPanel userStoryPanel = new JPanel(new BorderLayout());
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
        userStoryPanel.add(scrollPane, BorderLayout.CENTER);

        createUserStoryButton = new JButton("Crear User Story");
        createUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserStoryDialog();
            }
        });

        deleteUserStoryButton = new JButton("Eliminar User Story");
        deleteUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = userStories.get(selectedRow).getId();
                    // clientServer.deleteUserStory(id);
                    ((DefaultTableModel) usTable.getModel()).removeRow(selectedRow);
                    userStories.remove(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione una User Story para eliminar.", "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editUserStoryButton = new JButton("Editar User Story");
        editUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una User Story para editar.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UserStoryData selectedStory = userStories.get(selectedRow);
                int id = selectedStory.getId();
                String userStory = selectedStory.getUserStory();
                int estimation = selectedStory.getEstimation();
                int pbPriority = selectedStory.getPbPriority();
                // clientServer.modifyUserStory(id, userStory, estimation, pbPriority);
                // Actualizar datos de prueba
                selectedStory.setUserStory(userStory);
                selectedStory.setEstimation(estimation);
                selectedStory.setPbPriority(pbPriority);
                updateUserStoriesTable();
            }
        });

        JPanel userStoryButtonPanel = new JPanel(new FlowLayout());
        userStoryButtonPanel.add(createUserStoryButton);
        userStoryButtonPanel.add(deleteUserStoryButton);
        userStoryButtonPanel.add(editUserStoryButton);
        userStoryPanel.add(userStoryButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("User Stories", userStoryPanel);

        // Panel para Sprints
        JPanel sprintPanel = new JPanel(new BorderLayout());
        sprintTable = new JTable();
        JScrollPane sprintScrollPane = new JScrollPane(sprintTable);
        sprintPanel.add(sprintScrollPane, BorderLayout.CENTER);

        createSprintButton = new JButton("Crear Sprint");
        createSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSprintDialog();
            }
        });
        deleteSprintButton = new JButton("Eliminar Sprint");
        deleteSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = sprintTable.getSelectedRow();
                if (selectedRow != -1) {
                    //int sprintNum = sprints.get(selectedRow).getSprintNum();
                    //clientServer.deleteSprint(sprintNum);
                    ((DefaultTableModel) sprintTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione un Sprint para eliminar.", "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel sprintButtonPanel = new JPanel(new FlowLayout());
        sprintButtonPanel.add(createSprintButton);
        sprintButtonPanel.add(deleteSprintButton);
        sprintPanel.add(sprintButtonPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Sprints", sprintPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        updateUserStoriesTable();
        // updateSprintTable();
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

                UserStoryData newStory = new UserStoryData();
                newStory.setId(userStories.size() + 1); // Incrementar el ID para el nuevo dato de prueba
                newStory.setUserStory(title);
                newStory.setEstimation(estimation);
                newStory.setPbPriority(priority);

                userStories.add(newStory);
                updateUserStoriesTable();
                // clientServer.registerUserStory(newStory.getId(), title, estimation, priority);
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
            model.addRow(new Object[]{usd.getId(), usd.getUserStory(), usd.getPbPriority(), usd.getEstimation(), "Eliminar"});
        }
    }

    private void createSprintDialog() {

    }

    private void updateSprintTable() {

    }
}
