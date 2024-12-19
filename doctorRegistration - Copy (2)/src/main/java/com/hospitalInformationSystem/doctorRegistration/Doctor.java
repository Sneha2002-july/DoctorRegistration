package com.hospitalInformationSystem.doctorRegistration;

import java.time.LocalDate;

public class Doctor {
    private static int idCounter = 1;
    private int id; 
    private String name;
	private String contact;
	private String email;
	private String address;
	private LocalDate dob;
    private int age;
    private String department;
    private String specialization;
    private String qualification;
    private String startTime;
    private String endTime;
    private String availableFromDay;
    private String availableToDay;

    
    public Doctor( String name, String contact, String email, String address, LocalDate dob, int age,
			String department, String specialization, String qualification, String startTime, String endTime,
			String availableFromDay, String availableToDay) {
		super();
		this.id = idCounter++;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.dob = dob;
		this.age = age;
		this.department = department;
		this.specialization = specialization;
		this.qualification = qualification;
		this.startTime = startTime;
		this.endTime = endTime;
		this.availableFromDay = availableFromDay;
		this.availableToDay = availableToDay;
	}
	public Doctor() {
		super();
	}
	public static int getIdCounter() {
		return idCounter;
	}
	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getAge() {
        return age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public static void setIdCounter(int idCounter) {
		Doctor.idCounter = idCounter;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAvailableFromDay() {
		return availableFromDay;
	}
	public void setAvailableFromDay(String availableFromDay) {
		this.availableFromDay = availableFromDay;
	}
	public String getAvailableToDay() {
		return availableToDay;
	}
	public void setAvailableToDay(String availableToDay) {
		this.availableToDay = availableToDay;
	}

	public Object[] toArray() {
        return new Object[]{name, contact, email, address, dob, age, department, specialization,
                qualification, startTime, endTime, availableFromDay, availableToDay};
    }
}