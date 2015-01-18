package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class FieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("fieldSelectionTest");

        collection.drop();

        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(2)).
                                         append("y", new Random().nextInt(100)).
                                         append("z", new Random().nextInt(1000)));
        }

        /*
        * Here x will always be 0.
        * So no need to print it*/

        DBObject query =  QueryBuilder.start("x").is(0).
                                         and("y").greaterThan(10).lessThan(70).get();

        //DBCursor cursor = collection.find(query, new BasicDBObject("x", false)); // X will not Print
        DBCursor cursor = collection.find(query, new BasicDBObject("y", true).append("_id", false)); // Y will only Print

        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
    }

}
