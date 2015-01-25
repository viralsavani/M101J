package com.tengen.week_3.week_3.Morphia;

import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by VIRAL on 1/24/2015.
 */

/**
 *  Entity defines the entry point of MongoDB.
 *  "users" collection will be created.
 *  If value parameter is not provided then
 *  classname is stored.
 */

@Entity (value = "users", noClassnameStored = true)
@Indexes({
        @Index(value="username, -followers", name = "popular"),
        @Index(value = "lastActive", name = "idle", expireAfterSeconds = 100000000)
})
public class GitHubUser {
    /**
     * @Id equates to _Id in MongoDB.
     * Every documents need this @Id.
     */
    @Id
    public String username;
    public String fullName;
    /**
     * By default, Morphia uses the field name
     * as the value name in Mongo.
     * This can be overridden by using the
     * @Property annotation, and specifying a name.
     * It can be used to shorten the name in MongoDB, if
     * you have long java names for some reason.
     */
    @Property("since")
    public Date memberSince;
    public Date lastActive;
    @Reference(lazy = true)
    public List<Repository> repositories = new ArrayList<Repository>();
    public int followers = 0;
    public int following = 0;

    public GitHubUser(){
    }

    public GitHubUser(String username){
        this.username  = username;
    }
}
