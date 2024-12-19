package com.hospitalInformationSystem.doctorRegistration;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BrowserPanel extends JPanel {
    public BrowserPanel(CardLayout cardLayout, JPanel container, DoctorTableModel tableModel) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Doctor Browser"));

        JTable doctorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.add(new JLabel("Search:"));
        JTextField searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> tableModel.filter(searchField.getText().trim().toLowerCase()));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            searchField.setText("");
            tableModel.resetFilter();
        });

        searchPanel.add(searchButton);
        searchPanel.add(resetButton);
        add(searchPanel, BorderLayout.NORTH);

        JButton goToProfileButton = new JButton("Go to Profile Page");
        goToProfileButton.addActionListener(e -> cardLayout.show(container, "Profile"));
        add(goToProfileButton, BorderLayout.SOUTH);
    }
}