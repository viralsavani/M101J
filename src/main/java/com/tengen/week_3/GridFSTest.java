package com.tengen.week_3;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by VIRAL on 1/22/2015.
 */
public class GridFSTest {

    public static void main(String[] args) throws IOException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("week3");
        FileInputStream inputStream = null;

        GridFS video =  new GridFS(db, "videos");

        inputStream = new FileInputStream("D:\\video.mp4");

        GridFSInputFile fsInputFile = video.createFile(inputStream,"video.mp4");

        BasicDBObject meta = new BasicDBObject("description", "Avengers Age of Ultron Trailer");
        ArrayList <String> tags = new ArrayList<String>();
        tags.add("Avengers");
        tags.add("Marvel");
        tags.add("Trailer");
        meta.append("tags", tags);

        fsInputFile.setMetaData(meta);

        fsInputFile.save();

        System.out.println("Object _ID of the file saved :: " + fsInputFile.get("_id"));

        System.out.println("Saved file to MongoDB");

        GridFSDBFile gridFile = video.findOne(new BasicDBObject("filename", "video.mp4"));

        FileOutputStream outputStream = new FileOutputStream("video_copy.mp4");
        gridFile.writeTo(outputStream);

        System.out.println("File written back");




    }

}
