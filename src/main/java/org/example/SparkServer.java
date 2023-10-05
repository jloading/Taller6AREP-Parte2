package org.example;

import static spark.Spark.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import org.bson.Document;

public class SparkServer {
    public static void main(String... args) {
        port(getPort());
        get("logString", (req, res) -> {
            String value = req.queryParams("value");
            sendDocumentToDB(value);
            System.out.println("Request Has Been Received and the value is:" + value);
            Gson gson = new Gson();
            return gson.toJson(getLast10Strings());
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }

    private static void sendDocumentToDB(String value){

        MongoClient mongoClient = MongoClients.create("mongodb://db:27017");
        MongoDatabase database = mongoClient.getDatabase("arep-logs");
        MongoCollection<Document> collection = database.getCollection("logs");

        String currentDate =LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        Document document = new Document("string", value)
                .append("date", currentDate);

        collection.insertOne(document);

        mongoClient.close();
    }

    public static List<Document> getLast10Strings(){
        MongoClient mongoClient = MongoClients.create("mongodb://db:27017");
        MongoDatabase database = mongoClient.getDatabase("arep-logs");
        MongoCollection<Document> collection = database.getCollection("logs");

        List<Document> documents = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().limit(10).sort(Sorts.descending("date")).iterator()) {
            while (cursor.hasNext()) {
                documents.add(cursor.next());
            }
        }

        mongoClient.close();
        return documents;

    }



}
