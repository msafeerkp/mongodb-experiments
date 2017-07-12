package org.learning.mongodb.main;

import java.util.Arrays;

import org.bson.Document;
import org.learning.mongodb.dao.EmployeeDao;

import com.mongodb.Block;

public class ConsoleMain {
	
	public static void main(String[] args) {
		
		EmployeeDao employeeDao = new EmployeeDao();
		//employeeDao.createCollection("EMPLOYEE");
		
		System.out.println(":::::::::::::::::: Insert operation ::::::::::::::::::");
		Document document = new Document()
				.append("name", "Safeer")
				.append("employee-id", "123")
				.append("business-unit", "Platform")
				.append("hobbies", Arrays.asList("reading","football"))
				.append("address", new Document()
						.append("city", "bangalore")
						.append("state", "karnataka")
				);
		employeeDao.insertData(document);
		System.out.println(":::::::::::::::::: Insert operation completed ::::::::::::::::::");
		
		System.out.println("Number of objects in the collection is :: "+employeeDao.getCount());
		
		System.out.println(":::::::::::::::::: read all operation ::::::::::::::::::");
		
		employeeDao.readAll();
		
		System.out.println(":::::::::::::::::: read all operation completed ::::::::::::::::::");
		
		System.out.println(":::::::::::::::::: find employee with name as Safeer ::::::::::::::::::");
		employeeDao.findByName("Safeer");
		
		System.out.println(":::::::::::::::::: updating the name ::::::::::::::::::");
		employeeDao.updateName("Safeer","Siraj");
		
		System.out.println(":::::::::::::::::: find employee with name as Siraj ::::::::::::::::::");
		employeeDao.findByName("Siraj");
		
		System.out.println(":::::::::::::::::: deleting records with name as Siraj ::::::::::::::::::");
		//employeeDao.deleteByName("Siraj");
		
		System.out.println(":::::::::::::::::: read all operation ::::::::::::::::::");
		
		employeeDao.readAll();
		
		System.out.println(":::::::::::::::::: read all operation completed ::::::::::::::::::");
		
		System.out.println(":::::::::::::::::: creating index ::::::::::::::::::");
		
		employeeDao.createIndex("employee-id");
		
		System.out.println(":::::::::::::::::: index creation completed and indexes are ::::::::::::::::::");
		
		employeeDao.listIndexes();
	}

}
