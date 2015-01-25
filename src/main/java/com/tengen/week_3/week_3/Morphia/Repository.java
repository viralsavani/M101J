package com.tengen.week_3.week_3.Morphia;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by VIRAL on 1/24/2015.
 */

@Entity("repos")
public class Repository {
    @Id
    public String name;
    @Reference
    public Organization organization;
    @Reference
    public GitHubUser owner;

    public Repository(){
    }

    public Repository(final Organization organizations, final String name){
        this.organization = organization;
        this.name = organization.name + "/" + name;
    }
}
