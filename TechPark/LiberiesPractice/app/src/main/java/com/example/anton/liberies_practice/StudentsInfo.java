package com.example.anton.liberies_practice;

import java.util.ArrayList;

public class StudentsInfo {
    ArrayList<UserInfo> users=new ArrayList<>();
    String id="";
    String name="";
    String size="";

    public ArrayList<UserInfo> getUsers() {
        return users;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }
}
