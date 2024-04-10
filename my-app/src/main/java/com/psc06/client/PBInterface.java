package com.psc06.client;

import com.psc06.server.UserStory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PBInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable usTable;

    public PBInterface(List<UserStory> userStories) {
        setTitle("Product Backlog");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre de User Story");
        model.addColumn("Estimación");
        model.addColumn("Prioridad");

        for (UserStory us : userStories) {
            model.addRow(new Object[]{us.getId(), us.getUserStory(), us.getEstimation(), us.getPbPriority()});
        }

        usTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(usTable);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    //public static void main(String[] args) {
        // Datos de prueba
       // List<UserStory> userStories = new ArrayList<>();
       // userStories.add(new UserStory(1, "US1", 5, 1));
       // userStories.add(new UserStory(2, "US2", 8, 2));
       // userStories.add(new UserStory(3, "US3", 3, 3));

       // SwingUtilities.invokeLater(() -> new PBInterface(userStories));
   // }
}
