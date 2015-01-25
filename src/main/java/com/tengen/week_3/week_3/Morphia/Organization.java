package com.tengen.week_3.week_3.Morphia;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Version;
import org.mongodb.morphia.utils.IndexDirection;

import java.util.Date;

/**
 * Created by VIRAL on 1/24/2015.
 */

@Entity("orgs")
public class Organization {
    @Id
    public String name;
    @Indexed(value = IndexDirection.ASC, name = "", unique = false, dropDups = false, expireAfterSeconds = -1, background = false, sparse = false)
    public Date created;
    @Version("v")
    public long version;

    public Organization(){
    }

    public Organization(final String name){
        this.name = name;
    }
}
