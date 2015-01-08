package com.tengen;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by VIRAL on 1/7/2015.
 */
public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        //Bootstrap the application with Configuration Class
        Configuration configuration = new Configuration();

        //Set the class for template loading. Tell freeMarker configuration how to find  FreeMarker template
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class,"/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            final StringWriter writer = new StringWriter();
            Map<String, Object> helloMap = new HashMap<String, Object>();
            helloMap.put("name","Viral L. Savani");
            helloTemplate.process(helloMap, writer);
            //System.out.println(writer);

            Spark.get(new Route("/hello") {
                @Override
                public Object handle(final Request request, final Response response) {
                    return writer;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
