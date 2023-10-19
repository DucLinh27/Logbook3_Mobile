package com.example.logbook3new;

public class Person {


    private String name;
    private String dob;
    private String email;
    private int imageResourceId;

    public Person(String name, String dob, String email, int imageResourceId) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.imageResourceId = imageResourceId;
    }


    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }


}


