package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by VIRAL on 1/7/2015.
 */
public class HelloWorldMongoDBStyle {

    public static void main(String[] args) throws UnknownHostException {
        /* Logical connection to database. Creates a mongoClient running on server on localhost. ServerAddress only adds a bit of explicitness */
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

        /* Get an instance from client of database named course */
        DB database = client.getDB("course");

        //From database we get a collection
        DBCollection collection = database.getCollection("things");

        DBObject document = collection.findOne();
        System.out.println(document);

    }

}
