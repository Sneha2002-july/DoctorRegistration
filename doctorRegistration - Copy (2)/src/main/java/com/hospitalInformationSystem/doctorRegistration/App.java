package com.hospitalInformationSystem.doctorRegistration;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Doctor Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            CardLayout cardLayout = new CardLayout();
            JPanel container = new JPanel(cardLayout);
            DoctorTableModel tableModel = new DoctorTableModel();
            DoctorProfilePanel profilePanel = new DoctorProfilePanel(cardLayout, container, tableModel);
            BrowserPanel browserPanel = new BrowserPanel(cardLayout, container, tableModel);
            container.add(profilePanel, "Profile");
            container.add(browserPanel, "Browser");
            frame.add(container);
            frame.setVisible(true);
        });
    }
}