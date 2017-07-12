package org.learning.mongodb.connection;

import java.io.Closeable;
import java.io.IOException;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoConnectionManager implements Closeable{

	private MongoClient client;
	
	private static final MongoConnectionManager CONNECTION_MANAGER = new MongoConnectionManager();
	
	protected MongoConnectionManager() {
		client = new MongoClient();
	}
	
	public static MongoConnectionManager getInstance() {
		return CONNECTION_MANAGER;
	}
	
	@Override
	public void close() throws IOException {
		client.close();
		
	}
	
	public MongoDatabase getMongoDatabase(String databaseName) {
		return client.getDatabase(databaseName);
	}
	
}
