package com.hospitalInformationSystem.doctorRegistration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonHandler {
	private ObjectMapper mapper=new ObjectMapper();
	public JsonHandler() {
		mapper.registerModule(new JavaTimeModule());
	}
	String filePath = "DoctorsProfile.json";
	void writeToJson(ArrayList<Doctor> doctors) {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), doctors);
		} catch (StreamWriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	ArrayList<Doctor> readFromJson(){
		try {
			return mapper.readValue(new File(filePath), new TypeReference<ArrayList<Doctor>>() {});
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
