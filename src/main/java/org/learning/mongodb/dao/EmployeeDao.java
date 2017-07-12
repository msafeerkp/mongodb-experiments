package org.learning.mongodb.dao;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.learning.mongodb.connection.MongoConnectionManager;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class EmployeeDao {
	
	private final String COLLECTION_NAME = "EMPLOYEE";
	
	public void createCollection(String collectionName) {
		
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		database.createCollection(collectionName);
		System.out.println("collection is created . name :: "+collectionName);
		
	}
	
	public boolean insertData(Document document) {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		database.getCollection(COLLECTION_NAME).insertOne(document);
		return true;
	}
	
	public long getCount() {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		MongoCollection<Document> employeeCollection = database.getCollection(COLLECTION_NAME);
		return employeeCollection.count();
	}
	
	public void readAll(){
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		FindIterable<Document> iterator = database.getCollection(COLLECTION_NAME).find();
		MongoCursor<Document> cursor = iterator.iterator();
		while(cursor.hasNext()) {
			Document document = cursor.next();
			System.out.println(document.toJson());
		}
	}
	
	public void findByName(String name) {
		
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
		Block<Document> printBlock = (document) -> {
			System.out.println(document.toJson());
		};
		collection.find(Filters.eq("name", name)).forEach(printBlock);
		
	}
	
	public void updateName(String oldName, String newName) {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		UpdateResult result = database.getCollection(COLLECTION_NAME).
				updateMany(
						Filters.eq("name",oldName),
						new Document("$set", new Document("name",newName)
						));
		System.out.println("Number of records modified is ::" + result.getModifiedCount());
	}
	
	public void deleteByName(String name) {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		DeleteResult result = database.getCollection(COLLECTION_NAME).deleteMany(
						Filters.eq("name",name)
						);
		System.out.println("Number of records deleted is ::" + result.getDeletedCount());
	}
	
	public void createIndex(String field) {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		database.getCollection(COLLECTION_NAME).createIndex(new Document(field,1));
		
	}
	
	public void listIndexes() {
		MongoDatabase database = MongoConnectionManager.getInstance().getMongoDatabase("test");
		Block<Document> printBlock = (document)->{
			System.out.println(document.toJson());
		};
		database.getCollection(COLLECTION_NAME).listIndexes().forEach(printBlock);
		
	}

}
