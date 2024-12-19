package com.hospitalInformationSystem.doctorRegistration;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;

import com.toedter.calendar.JDateChooser;

public class DoctorProfilePanel extends JPanel {
    private JTextField nameField, contactField, emailField, addressField, ageField, qualificationField;
    private JComboBox<String> departmentDropdown, specializationDropdown;
    private JSpinner startTimeSpinner, endTimeSpinner, availableFromDaySpinner, availableToDaySpinner;
    private JDateChooser dobPicker;

    public DoctorProfilePanel(CardLayout cardLayout, JPanel container, DoctorTableModel tableModel) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Doctor Profile"));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Input fields
        nameField = new JTextField(20);
        contactField = new JTextField(20);
        emailField = new JTextField(20);
        addressField = new JTextField(20);
        ageField = new JTextField(20);
        qualificationField = new JTextField(20);

        departmentDropdown = new JComboBox<>(new String[]{"Cardiology", "Dermatology", "Neurology", "Pediatrics", "Ophthalmology"});
        specializationDropdown = new JComboBox<>(new String[]{"Pediatric Cardiology", "Electropsysiology", "Neuroimmunology", "epileptologist", " Pediatric Ophthalmologist", "Sports Medicine","Cosmetic dermatology","Pediatric neurology","Gastroenterologist"});
        
        startTimeSpinner = new JSpinner(new SpinnerDateModel());
        endTimeSpinner = new JSpinner(new SpinnerDateModel());
        availableFromDaySpinner = new JSpinner(new SpinnerListModel(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
        availableToDaySpinner = new JSpinner(new SpinnerListModel(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));

        // Format spinners
        startTimeSpinner.setEditor(new JSpinner.DateEditor(startTimeSpinner, "HH:mm"));
        endTimeSpinner.setEditor(new JSpinner.DateEditor(endTimeSpinner, "HH:mm"));

        
        dobPicker = new JDateChooser();

        // Add fields to the form
        addField(formPanel, gbc, "Doctor Name:", nameField, 0);
        addField(formPanel, gbc, "Contact:", contactField, 1);
        addField(formPanel, gbc, "Email:", emailField, 2);
        addField(formPanel, gbc, "Address:", addressField, 3);
        addField(formPanel, gbc, "Age:", ageField, 4);
        addField(formPanel, gbc, "Qualification:", qualificationField, 5);
        addField(formPanel, gbc, "Department:", departmentDropdown, 6);
        addField(formPanel, gbc, "Specialization:", specializationDropdown, 7);
        addField(formPanel, gbc, "Start Time:", startTimeSpinner, 8);
        addField(formPanel, gbc, "End Time:", endTimeSpinner, 9);
        addField(formPanel, gbc, "Date of Birth:", dobPicker, 10);
        addField(formPanel, gbc, "Available From Day:", availableFromDaySpinner, 11);
        addField(formPanel, gbc, "Available To Day:", availableToDaySpinner, 12);

        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        // Buttons for Save and Clear
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveDoctorProfile(tableModel));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearFields());
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        JButton goToBrowserButton = new JButton("Go to Browser Page");
        goToBrowserButton.addActionListener(e -> cardLayout.show(container, "Browser"));
        add(goToBrowserButton, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JComponent field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        panel.add(field, gbc);
    }

    private void saveDoctorProfile(DoctorTableModel tableModel) {
        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String email = emailField.getText().trim();
        String address = addressField.getText().trim();
        String ageText = ageField.getText().trim();
        String qualification = qualificationField.getText().trim();
        String department = (String) departmentDropdown.getSelectedItem();
        String specialization = (String) specializationDropdown.getSelectedItem();
        Date startTime = (Date) startTimeSpinner.getValue();
        Date endTime = (Date) endTimeSpinner.getValue();
        String availableFromDay = (String) availableFromDaySpinner.getValue();
        String availableToDay = (String) availableToDaySpinner.getValue();
        LocalDate dob = dobPicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedStartTime = timeFormat.format(startTime);
        String formattedEndTime = timeFormat.format(endTime);

        if (name.isEmpty() || contact.isEmpty() || email.isEmpty() || address.isEmpty() || ageText.isEmpty() ||
                qualification.isEmpty() || dob==null) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageText);
            if (age <= 0) {
                JOptionPane.showMessageDialog(this, "Age must be a positive number!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int nextId=tableModel.getRowCount()+1;
            Doctor doctor = new Doctor(name, contact, email, address, dob, age, department, specialization,
                    qualification, formattedStartTime, formattedEndTime, availableFromDay, availableToDay);
            tableModel.addDoctor(doctor);
            JOptionPane.showMessageDialog(this, "Doctor Profile Saved Successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a valid number!", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
        emailField.setText("");
        addressField.setText("");
        ageField.setText("");
        qualificationField.setText("");
        departmentDropdown.setSelectedIndex(0);
        specializationDropdown.setSelectedIndex(0);
        startTimeSpinner.setValue(new SpinnerDateModel().getDate());
        endTimeSpinner.setValue(new SpinnerDateModel().getDate());   
        availableFromDaySpinner.setValue("Monday");
        availableToDaySpinner.setValue("Monday");
    }
}