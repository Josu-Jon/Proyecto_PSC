package com.psc06.Interfaces;

import com.psc06.server.UserStory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PrioriceUS extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable usTable;
    private JButton createButton; 
    private JButton prioritizeButton;

    public PrioriceUS() {
        setTitle("Priorizar User Stories");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLayout(new BorderLayout());

        // Datos de prueba
        List<UserStory> userStories = new ArrayList<>();
        userStories.add(new UserStory(1, "US1", 0, 0));
        userStories.add(new UserStory(2, "US2", 0, 0));
        userStories.add(new UserStory(3, "US3", 0, 0));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre de User Story");
        model.addColumn("Prioridad");

        for (UserStory us : userStories) {
            model.addRow(new Object[]{us.getUserStory(), us.getPbPriority()});
        }

        usTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(usTable);
        add(scrollPane, BorderLayout.CENTER);


        createButton = new JButton("+");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                new USInterface();
            }
        });
        add(createButton, BorderLayout.NORTH); 

        prioritizeButton = new JButton("Priorizar");
        add(prioritizeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrioriceUS::new);
    }
}


