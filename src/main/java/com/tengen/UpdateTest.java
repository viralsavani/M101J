package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class UpdateTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("UpdateTest");

        collection.drop();
        List<String> names = Arrays.asList("Viral", "Chokshi", "Arpit" ,"Ishan");

        for (String name : names){
            collection.insert(new BasicDBObject("_id", name));
        }


        collection.update(new BasicDBObject("_id","Viral"),
                new BasicDBObject("age",22));

    /* * This will overwrite the update done by age 22 as it is whole sale.
       * To prevent this use $set

        collection.update(new BasicDBObject("_id","Viral"),
                new BasicDBObject("age",22));
    */

        //collection.remove(new BasicDBObject("_id","Viral"));

        collection.update(new BasicDBObject("_id","Kirtan"),
                new BasicDBObject("$set", new BasicDBObject("Gender","M")), true, false);

        collection.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("Title","Engineer")), false, true);




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
}
