package com.tengen.week_2;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");

        collection.drop();

        DBObject dbObject = new BasicDBObject("Name", "Viral").append("Method", "From Java Drive");
        DBObject dbObject2 = new BasicDBObject("_id", new ObjectId()).append("Name", "Anand").append("Method", "AsList");

        //dbObject.removeField("_id"); //Deletes the entire document

        System.out.println(dbObject);
        collection.insert(Arrays.asList(dbObject,dbObject2));
        System.out.println(dbObject);
    }
}
