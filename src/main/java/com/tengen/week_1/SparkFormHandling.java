package com.tengen.week_1;


import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by VIRAL on 1/8/2015.
 */
public class SparkFormHandling {
    public static void main(String[] args) {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class,"/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                Map<String, Object> fruitMap = new HashMap<String, Object>();
                fruitMap.put("fruits", Arrays.asList("Apple","Mango","Banana","Peach"));
                StringWriter writer = new StringWriter();

                try {
                    Template fruitTemplate = configuration.getTemplate("fruitPicker.ftl");
                    fruitTemplate.process(fruitMap, writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });

        //This is post request handling
        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "<h1>Please select one Fruit</h1>";
                }else{
                    return "Fruit Selected is "+fruit;
                }
            }
        });
    }
}
