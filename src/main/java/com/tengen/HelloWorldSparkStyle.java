package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by VIRAL on 1/7/2015.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        /* Route is a pattern. We are saying this "/" is the pattern that this route should match. So its the homepage for this particular web application */
        Spark.get(new Route("/hello") {
            @Override
            public Object handle(final Request request, final Response response) {
                return "Hello World From Spark";
            }
        });

        Spark.get(new Route("/by") {
            @Override
            public Object handle(final Request request, final Response response) {
                /* Sparks call .toString and returns an web page. But for HTML in singe will not be possible so we use template */
                return "By World From Spark";
            }
        });
    }
}
