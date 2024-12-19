package com.hospitalInformationSystem.doctorRegistration;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DoctorTableModel extends AbstractTableModel {
    private ArrayList<Doctor> doctors;
    private List<Doctor> filteredDoctors;
    private final String[] columnNames = {"ID", "Name", "Contact", "Email","Address","DOB","Age","Department", "Specialization","Qualification", "Start Time", "End Time","Available from","Availabel To"};
  private JsonHandler jsonHandler;
    public DoctorTableModel() {
    	jsonHandler = new JsonHandler();
    	this.doctors = jsonHandler.readFromJson();
    	this.filteredDoctors = jsonHandler.readFromJson();
    	if(doctors==null){
    		doctors = new ArrayList<>();
    	}
    	if(filteredDoctors==null) {
    		this.filteredDoctors = new ArrayList<>();
    	}
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        jsonHandler.writeToJson(doctors);
        filteredDoctors.add(doctor);
        fireTableDataChanged();
    }

    public void filter(String query) {
        filteredDoctors.clear();
        for (Doctor doctor : doctors) {
            if (String.valueOf(doctor.getId()).contains(query)
                    || doctor.getName().toLowerCase().contains(query)
                    || doctor.getContact().toLowerCase().contains(query)
                    ||doctor.getEmail().toLowerCase().contains(query)
                    || doctor.getSpecialization().toLowerCase().contains(query)) {
                filteredDoctors.add(doctor);
            }
        }
        fireTableDataChanged();
    }

    public void resetFilter() {
        filteredDoctors.clear();
        filteredDoctors.addAll(doctors);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return filteredDoctors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doctor doctor = filteredDoctors.get(rowIndex);
        switch (columnIndex) {
            case 0:return doctor.getId();
            case 1: return doctor.getName();
            case 2: return doctor.getContact();
            case 3: return doctor.getEmail();
            case 4: return doctor.getAddress();
            case 5: return doctor.getDob();
            case 6: return doctor.getAge();
            case 7: return doctor.getDepartment();
            case 8: return doctor.getSpecialization();
            case 9: return doctor.getQualification();
            case 10: return doctor.getStartTime();
            case 11: return doctor.getEndTime();
            case 12: return doctor.getAvailableFromDay();
            case 13: return doctor.getAvailableToDay();
            default: return null;
        }
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}