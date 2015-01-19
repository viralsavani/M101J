package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class FindAndModify {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("FindAndModify");

        collection.drop();

        //Find the document modify it and return it in one atomic operation

        final String counterId = "abc";
        int first;
        int numNeeded;

        numNeeded = 2;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + ( first + numNeeded - 1 ) );

        numNeeded = 3;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + ( first + numNeeded - 1 ) );

        numNeeded = 10;
        first = getRange(counterId, numNeeded, collection);
        System.out.println("Range: " + first + "-" + ( first + numNeeded - 1 ) );


        System.out.println();
        DBCursor cursor = collection.find();
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
    }

    public static int getRange(String id, int range, DBCollection collection){
        DBObject doc = collection.findAndModify(
                new BasicDBObject("_id", id), null, null ,false,
                new BasicDBObject("$inc", new BasicDBObject("counter", range)),
                true, true);
        return (Integer) doc.get("counter") - range + 1;
    }
}
