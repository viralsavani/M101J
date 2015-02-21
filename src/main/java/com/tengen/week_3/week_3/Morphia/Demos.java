package com.tengen.week_3.week_3.Morphia;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by VIRAL on 1/24/2015.
 */
public class Demos {
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYY");
    private GitHubUser viral;
    private Date date;

    public Demos() throws ParseException {
        date = sdf.parse("4-18-2013");
    }

    public void basicUser(){
        viral = new GitHubUser("Viral Savani");
        viral.fullName = "Viral Laxman Savani";
        viral.memberSince = date;
        viral.following = 1;
    }
}