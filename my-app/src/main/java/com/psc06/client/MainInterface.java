package com.psc06.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainInterface() {
        setTitle("Bienvenido/a");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);


        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Bienvenido/a");
        titlePanel.add(titleLabel);


        mainPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JButton createButton = new JButton("Crear Historia de Usuario");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new USInterface();
            }
        });
        buttonPanel.add(createButton);

        JButton prioritizeButton = new JButton("Priorizar y Estimar Historias de Usuario");
        prioritizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrioriceUS();
            }
        });
        buttonPanel.add(prioritizeButton);

        JButton viewBacklogButton = new JButton("Ver Product Backlog");
        viewBacklogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PBInterface(null);
            }
        });
        buttonPanel.add(viewBacklogButton);


        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainInterface::new);
    }
}


