package com.platiper.test.mongodb;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/hello" })
public class HelloAppEngine extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String password = "INSERT_PASSWORD_HERE";
		final String uriString = "INSERT_MONGODB_CONNECTION_STRING_HERE";
		
		MongoClient mongoClient = MongoClients.create(uriString);
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("inventory");

		Document canvas = new Document("item", "canvas").append("qty", 100).append("tags",
				Collections.singletonList("cotton"));

		Document size = new Document("h", 28).append("w", 35.5).append("uom", "cm");
		canvas.put("size", size);

		collection.insertOne(canvas);

		mongoClient.close();

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("Hello App Engine!\r\n");
	}
}