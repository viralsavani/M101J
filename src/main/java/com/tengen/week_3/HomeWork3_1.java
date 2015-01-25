package com.tengen.week_3;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

/**
 * Created by VIRAL on 1/24/2015.
 */
public class HomeWork3_1 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        DB studentDB = mongoClient.getDB("school");
        DBCollection students = studentDB.getCollection("students");

        int count = (int) students.count();
        DBObject query = new BasicDBObject();
        DBCursor cursor = students.find(query);

        try {
            DBObject cur;
            int counter = 0;
            while (cursor.hasNext()) {
                cur = cursor.next();
                List<DBObject> scoresList = (List<DBObject>) cur.get("scores");
                DBObject minimumHomeworkScore = minimumHomework(scoresList);
                students.update(new BasicDBObject("_id", cur.get("_id")),
                                                    new BasicDBObject("$pull", new BasicDBObject("scores", minimumHomeworkScore)), true, false);
            }
        }
        finally {
            cursor.close();
        }
    }

    private static DBObject minimumHomework(List <DBObject> scores){
        DBObject minimumHomework = null;
        for(DBObject  object : scores){
            if(object.get("type").equals("homework")){
                minimumHomework = minimumScore(minimumHomework,object);
            }
        }
        return minimumHomework;
    }

    private static DBObject minimumScore(DBObject oldScore, DBObject newScore){
        if(oldScore == null){
            return newScore;
        }

        if(newScore == null){
            return oldScore;
        }

        Double oldDouble  = (Double) oldScore.get("score");
        Double newDouble  = (Double) newScore.get("score");

        if(Double.compare(oldDouble, newDouble) > 0){
            return newScore;
        }else{
            return oldScore;
        }
    }
}
