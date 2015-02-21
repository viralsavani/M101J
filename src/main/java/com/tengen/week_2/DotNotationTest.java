package com.tengen.week_2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class DotNotationTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection lines = courseDB.getCollection("DotNotationTest");

        lines.drop();

        // Ten lines with random start and end points.
        // In random step is 10.

        for (int i = 0; i < 10; i++) {
            lines.insert(new BasicDBObject("_id", i)
                                .append("start",
                                        new BasicDBObject("x", new Random().nextInt(2))
                                                        .append("y", new Random().nextInt(90)+10)
                                )
                                .append("end",
                                        new BasicDBObject("x", new Random().nextInt(2))
                                                    .append("y", new Random().nextInt(90)+10)
                                )
            );

        }

        //QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(50);
        QueryBuilder builder = QueryBuilder.start();
        //DBCursor cursor = lines.find(builder.get(), new BasicDBObject("end.y","true").append("_id", false));
        DBCursor cursor = lines.find(builder.get())
                            .sort(new BasicDBObject("start.x", 1).append("start.y",-1))
                            .skip(2)
                            .limit(10);
        try{
            while (cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
    }

//    public static void main(String[] args) throws UnknownHostException {
//        MongoClient client = new MongoClient();
//        DB courseDB = client.getDB("students");
//        DBCollection collection = courseDB.getCollection("grades");
//
//        //QueryBuilder builder = QueryBuilder.start("");
//
//        DBObject query = new BasicDBObject("type", "homework");
//        DBCursor cursor = collection.find(query).sort(new BasicDBObject("student_id", 1).append("score",1));
//
//        boolean flag = true;
//        Object id = 0;
//        while(cursor.hasNext()){
//            DBObject cur = cursor.next();
//            collection.remove(new BasicDBObject("_id", cur.get("_id")));
//            cursor.next();
//            System.out.println(cur);
//        }
//    }

}
