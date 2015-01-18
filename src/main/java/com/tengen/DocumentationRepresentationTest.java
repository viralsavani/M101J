package com.tengen;

import com.mongodb.BasicDBObject;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by VIRAL on 1/18/2015.
 */
public class DocumentationRepresentationTest {

    public static void main(String[] args) {

        /* * Whenever you want to create an instance
           * of DBObject always
           * create instance of BasicDBObject
          */
        BasicDBObject doc = new BasicDBObject();

        doc.put("username", "Viral");
        doc.put("randomDate", new Date(21212));
        doc.put("interests", Arrays.asList("basketball", "drumming"));
        doc.put("Boolean", true);
        doc.put("Integer" ,12);

        /*  Basic Object ultimately extends LinkedHashMap.
        *   Thus it follows the insertion order.
        *   Which gurantess the ordering of keys in MongoDB
        *   Which is suitable for insert() in MongoDB
        * */
        doc.put("Address",  new BasicDBObject("Street","Ximino Avenue").
                            append("Apt on","3").
                            append("ZipCode",90815));


    }

}
