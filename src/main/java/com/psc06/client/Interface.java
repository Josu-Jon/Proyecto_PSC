package com.psc06.client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import com.psc06.pojo.UserStoryData;
import com.psc06.pojo.SprintData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Interface class. Defines the graphical interface of the project manager.
 */
public class Interface extends JPanel {
    private JTabbedPane tabbedPane;
    private JTable usTable;
    private JTable sprintTable;
    private JButton createUserStoryButton;
    private JButton deleteUserStoryButton;
    private JButton createSprintButton;
    private JButton editSprintButton;
    private JButton deleteSprintButton;
    private JButton editUserStoryButton;

    private List<UserStoryData> userStories = new ArrayList<>();
    private List<SprintData> sprints = new ArrayList<>();

    private ClientServer clientServer;

    public Interface(ClientServer clientServerAux) {
        clientServer = clientServerAux;
        tabbedPane = new JTabbedPane();
        userStories = clientServer.getAllUserStories();

        // Datos de prueba
        UserStoryData usd1 = new UserStoryData();
        usd1.setId(1);
        usd1.setUserStory("Crear cliente y servidor");
        usd1.setEstimation(5);
        usd1.setPbPriority(2);

        UserStoryData usd2 = new UserStoryData();
        usd2.setId(2);
        usd2.setUserStory("Crear pom");
        usd2.setEstimation(2);
        usd2.setPbPriority(7);

        UserStoryData usd3 = new UserStoryData();
        usd3.setId(3);
        usd3.setUserStory("Diseñar interfaz de usuario");
        usd3.setEstimation(3);
        usd3.setPbPriority(5);

        userStories.add(usd1);
        userStories.add(usd2);
        userStories.add(usd3);

        // Panel para User Stories
        JPanel userStoryPanel = new JPanel(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Título");
        model.addColumn("Prioridad");
        model.addColumn("Estimación");

        /**
         * JTable para mostrar las User Stories
         */
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

        /**
         * Elimina una User Story seleccionada
         * 
         */
        deleteUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = userStories.get(selectedRow).getId();
                    clientServer.deleteUserStory(id);
                    ((DefaultTableModel) usTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione una User Story para eliminar.",
                            "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editUserStoryButton = new JButton("Editar User Story");
        /**
         * Edita una User Story seleccionada
         */
        editUserStoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = usTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una User Story para editar.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UserStoryData selectedStory = userStories.get(selectedRow);

                JFrame editDialog = new JFrame("Editar User Story");
                editDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editDialog.setSize(300, 200);
                editDialog.setLayout(new GridLayout(4, 2));

                JLabel titleLabel = new JLabel("Título:");
                JTextField titleField = new JTextField(selectedStory.getUserStory());
                JLabel priorityLabel = new JLabel("Prioridad:");
                JTextField priorityField = new JTextField(String.valueOf(selectedStory.getPbPriority()));
                JLabel estimationLabel = new JLabel("Estimación:");
                JTextField estimationField = new JTextField(String.valueOf(selectedStory.getEstimation()));

                JButton saveButton = new JButton("Guardar");
                /**
                 * Guarda los cambios realizados en la User Story seleccionada
                 */
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String title = titleField.getText();
                            int priority = Integer.parseInt(priorityField.getText());
                            int estimation = Integer.parseInt(estimationField.getText());

                            selectedStory.setUserStory(title);
                            selectedStory.setPbPriority(priority);
                            selectedStory.setEstimation(estimation);

                            updateUserStoriesTable();
                            clientServer.modifyUserStory(selectedStory.getId(), title, estimation, priority);
                            editDialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(editDialog,
                                    "Prioridad y Estimación deben ser valores numéricos.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                /**
                 * Cancela la edición de la User Story
                 
                 */
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editDialog.dispose();
                    }
                });

                editDialog.add(titleLabel);
                editDialog.add(titleField);
                editDialog.add(priorityLabel);
                editDialog.add(priorityField);
                editDialog.add(estimationLabel);
                editDialog.add(estimationField);
                editDialog.add(cancelButton);
                editDialog.add(saveButton);

                editDialog.setVisible(true);
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

        // Datos de prueba
        SprintData sprint1 = new SprintData();
        sprint1.setSprintNum(1);
        sprint1.setStartDate("2023-03-01");
        sprint1.setEndDate("2023-03-15");
        sprint1.addUserStory(usd1);
        sprint1.addUserStory(usd2);

        SprintData sprint2 = new SprintData();
        sprint2.setSprintNum(2);
        sprint2.setStartDate("2023-03-16");
        sprint2.setEndDate("2023-03-30");
        sprint2.addUserStory(usd3);

        sprints.add(sprint1);
        sprints.add(sprint2);

        DefaultTableModel sprintModel = new DefaultTableModel();
        sprintModel.addColumn("Número de Sprint");
        sprintModel.addColumn("Fecha de Inicio");
        sprintModel.addColumn("Fecha de Fin");
        sprintModel.addColumn("User Stories");

        /**
         * JTable para mostrar los Sprints
         
         */
        sprintTable = new JTable(sprintModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        createSprintButton = new JButton("Crear Sprint");
        /**
         * Crea un nuevo Sprint
         */
        createSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSprintDialog();
            }
        });

        deleteSprintButton = new JButton("Eliminar Sprint");
        /**
         * Elimina un Sprint seleccionado
         */
        deleteSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = sprintTable.getSelectedRow();
                if (selectedRow != -1) {
                    int sprintNum = sprints.get(selectedRow).getSprintNum();
                    clientServer.deleteSprint(sprintNum);
                    ((DefaultTableModel) sprintTable.getModel()).removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Interface.this, "Por favor, seleccione un Sprint para eliminar.",
                            "Selección Incorrecta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        editSprintButton = new JButton("Editar Sprint");
        /**
         * Edita un Sprint seleccionado
         */
        editSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = sprintTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un Sprint para editar.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SprintData selectedSprint = sprints.get(selectedRow);

                JFrame editDialog = new JFrame("Editar Sprint");
                editDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editDialog.setSize(400, 300);
                editDialog.setLayout(new GridLayout(5, 2));

                JLabel sprintNumLabel = new JLabel("Número de Sprint:");
                JTextField sprintNumField = new JTextField(String.valueOf(selectedSprint.getSprintNum()));
                sprintNumField.setEditable(false);

                JLabel storiesLabel = new JLabel("Historias de Usuario:");
                JList<String> storiesList = new JList<>(
                        userStories.stream().map(UserStoryData::getUserStory).toArray(String[]::new));
                storiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                JScrollPane storiesScrollPane = new JScrollPane(storiesList);

                JLabel startDateLabel = new JLabel("Fecha de Inicio:");
                JTextField startDateField = new JTextField(selectedSprint.getStartDate());

                JLabel endDateLabel = new JLabel("Fecha de Fin:");
                JTextField endDateField = new JTextField(selectedSprint.getEndDate());

                JButton saveButton = new JButton("Guardar");
                /**
                 * Guarda los cambios realizados en el Sprint seleccionado
                 */
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String startDate = startDateField.getText();
                            String endDate = endDateField.getText();
                            if (!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
                                JOptionPane.showMessageDialog(editDialog, "El formato de fecha debe ser yyyy-MM-dd",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if (!isEndDateAfterStartDate(startDate, endDate)) {
                                JOptionPane.showMessageDialog(editDialog,
                                        "La fecha de fin debe ser posterior a la fecha de inicio", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            selectedSprint.setStartDate(startDate);
                            selectedSprint.setEndDate(endDate);
                            selectedSprint.clearUserStories();
                            for (String selectedStoryTitle : storiesList.getSelectedValuesList()) {
                                for (UserStoryData usd : userStories) {
                                    if (usd.getUserStory().equals(selectedStoryTitle)) {
                                        selectedSprint.addUserStory(usd);
                                        break;
                                    }
                                }
                            }

                            updateSprintTable();
                            clientServer.modifySprint(selectedSprint.getSprintNum());
                            editDialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(editDialog, "Error al guardar el sprint.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        editDialog.dispose();
                    }
                });

                editDialog.add(sprintNumLabel);
                editDialog.add(sprintNumField);
                editDialog.add(storiesLabel);
                editDialog.add(storiesScrollPane);
                editDialog.add(startDateLabel);
                editDialog.add(startDateField);
                editDialog.add(endDateLabel);
                editDialog.add(endDateField);
                editDialog.add(cancelButton);
                editDialog.add(saveButton);

                editDialog.setVisible(true);
            }
        });
        JPanel sprintButtonPanel = new JPanel(new FlowLayout());
        sprintButtonPanel.add(createSprintButton);
        sprintButtonPanel.add(deleteSprintButton);
        sprintButtonPanel.add(editSprintButton);

        sprintPanel.add(sprintButtonPanel, BorderLayout.SOUTH);
        JScrollPane sprintScrollPane = new JScrollPane(sprintTable);
        sprintPanel.add(sprintScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Sprints", sprintPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        updateUserStoriesTable();
        updateSprintTable();
    }

    /**
     * Crea un diálogo para crear una nueva User Story
     */
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
        /**
         * Crea una nueva User Story con los datos ingresados
         */
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                int priority, estimation;

                try {
                    priority = Integer.parseInt(priorityField.getText());
                    estimation = Integer.parseInt(estimationField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Prioridad y Estimación deben ser valores numéricos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int newId = getNextId();
                UserStoryData newStory = new UserStoryData();
                newStory.setId(newId);
                newStory.setUserStory(title);
                newStory.setEstimation(estimation);
                newStory.setPbPriority(priority);

                userStories.add(newStory);
                updateUserStoriesTable();
                clientServer.registerUserStory(1, title, estimation, priority);
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

    /**
     * Actualiza la tabla de User Stories
     */
    private void updateUserStoriesTable() {
        DefaultTableModel model = (DefaultTableModel) usTable.getModel();
        model.setRowCount(0);
        for (UserStoryData usd : userStories) {
            model.addRow(new Object[] { usd.getId(), usd.getUserStory(), usd.getPbPriority(), usd.getEstimation() });
        }
    }


    /**
     * Crea un diálogo para crear un nuevo Sprint
     */
    private void createSprintDialog() {
        JFrame dialog = new JFrame("Crear Nuevo Sprint");
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        JLabel sprintNumLabel = new JLabel("Número de Sprint:");
        JTextField sprintNumField = new JTextField();

        JLabel storiesLabel = new JLabel("Historias de Usuario:");
        JList<String> storiesList = new JList<>(
                userStories.stream().map(UserStoryData::getUserStory).toArray(String[]::new));
        storiesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane storiesScrollPane = new JScrollPane(storiesList);

        JLabel startDateLabel = new JLabel("Fecha de Inicio:");
        JTextField startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("Fecha de Fin:");
        JTextField endDateField = new JTextField();

        JButton createButton = new JButton("Crear");
        /**
         * Crea un nuevo Sprint con los datos ingresados
         */
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sprintNum = Integer.parseInt(sprintNumField.getText());
                    String startDate = startDateField.getText();
                    String endDate = endDateField.getText();
                    if (!isValidDateFormat(startDate) || !isValidDateFormat(endDate)) {
                        JOptionPane.showMessageDialog(dialog, "El formato de fecha debe ser yyyy-MM-dd", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!isEndDateAfterStartDate(startDate, endDate)) {
                        JOptionPane.showMessageDialog(dialog, "La fecha de fin debe ser posterior a la fecha de inicio",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SprintData newSprint = new SprintData();
                    newSprint.setSprintNum(sprintNum);
                    newSprint.setStartDate(startDate);
                    newSprint.setEndDate(endDate);

                    for (String selectedStoryTitle : storiesList.getSelectedValuesList()) {
                        for (UserStoryData usd : userStories) {
                            if (usd.getUserStory().equals(selectedStoryTitle)) {
                                newSprint.addUserStory(usd);
                                break;
                            }
                        }
                    }

                    sprints.add(newSprint);
                    updateSprintTable();
                    clientServer.registerSprint(sprintNum);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "El número de Sprint debe ser un valor numérico.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        /**
         * Cancela la creación del Sprint
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(sprintNumLabel);
        dialog.add(sprintNumField);
        dialog.add(storiesLabel);
        dialog.add(storiesScrollPane);
        dialog.add(startDateLabel);
        dialog.add(startDateField);
        dialog.add(endDateLabel);
        dialog.add(endDateField);
        dialog.add(cancelButton);
        dialog.add(createButton);

        dialog.setVisible(true);
    }

    /**
     * Actualiza la tabla de Sprints
     */
    private void updateSprintTable() {
        DefaultTableModel model = (DefaultTableModel) sprintTable.getModel();
        model.setRowCount(0);
        for (SprintData sprint : sprints) {
            String userStoriesStr = String.join(", ",
                    sprint.getUserStories().stream().map(UserStoryData::getUserStory).toArray(String[]::new));
            model.addRow(
                    new Object[] { sprint.getSprintNum(), sprint.getStartDate(), sprint.getEndDate(), userStoriesStr });
        }
    }

    /**
     * Muestra la ventana de la interfaz
     */
    public void mostrarVentana() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Gestor de Proyectos");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.add(Interface.this);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Comprueba si la fecha de fin es posterior a la fecha de inicio
     * @param startDateText Fecha de inicio
     * @param endDateText Fecha de fin
     * @return true si la fecha de fin es posterior a la fecha de inicio, false en caso contrario
     */
    private boolean isEndDateAfterStartDate(String startDateText, String endDateText) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateText);
            Date endDate = sdf.parse(endDateText);

            return endDate.compareTo(startDate) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Comprueba si la fecha tiene el formato correcto
     * @param dateString Fecha
     * @return true si la fecha tiene el formato correcto, false en caso contrario
     */
    private boolean isValidDateFormat(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Obtiene el siguiente ID para una User Story
     * @return Siguiente ID
     */
    private int getNextId() {

        int maxId = 0;
        for (UserStoryData story : userStories) {
            maxId = Math.max(maxId, story.getId());
        }

        return maxId + 1;
    }
}
