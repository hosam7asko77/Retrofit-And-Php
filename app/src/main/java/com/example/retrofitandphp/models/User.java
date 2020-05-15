package com.example.retrofitandphp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    private Integer id;
    private  String name;
    private String password;
    private String email;


    public User(Integer id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
