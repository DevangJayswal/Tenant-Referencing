package com.example.devang.tenantreferencing.model;

/**
 * Created by Devang on 6/14/2016.
 */
public class Country {
    public static final String TAG = "Enquiry";
    private static final long serialVersionUID = -7406082437623008161L;

    private long id;
    private String name;

    public Country(){}

    public Country(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
