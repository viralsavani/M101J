package com.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created by VIRAL on 1/7/2015.
 */
public class HelloWorldMongoFreeMarkerSparkStyle {
    public static void main(String[] args) throws UnknownHostException {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerSparkStyle.class,"/");

        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("things");


        Spark.get(new Route("/hello") {
            @Override
            public Object handle(final Request request, final Response response) {
                final StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    DBObject document = collection.findOne();

                    //.process expects a map. .findOne returns concrete class BasicDBObject extends LinkedHashMap which implements Map. So FreeMarker will accept "document"
                    helloTemplate.process(document, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
