package com.tengen.week_4;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by VIRAL on 2/1/2015.
 */
public class BigDatabaseOperations {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB bigDB = client.getDB("bigDatabase");
        DBCollection collection = bigDB.getCollection("bigCollection");

        BasicDBObject query = new BasicDBObject("a", 77);
        query.append("b", 9);

        DBObject doc = collection.find(query).hint("a_1_b_1").explain();

        for(String s: doc.keySet()){
            System.out.printf("%25s:%s\n",s, doc.get(s));
        }
    }
}
