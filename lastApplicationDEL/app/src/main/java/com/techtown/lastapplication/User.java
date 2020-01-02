package com.techtown.lastapplication;

import java.io.Serializable;

public class User implements Serializable {

    public String id, pw, name, phone;

    public User(String id, String pw, String name, String phone){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.phone = phone;
    }


}
