package com.example.anton.liberies_practice;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserInfo implements Comparable<UserInfo>{
    @Override
    public int compareTo(UserInfo userInfo){
        return (age.compareTo(userInfo.age));//ComparisonChain.start().compare(age,userInfo.age).result();
    }
    private String fullName="";
    private String age="";
    private String weight="";
    private String gender="";
    @SerializedName("married")
    private String relation="";
    private ArrayList<String> users=new ArrayList<>();

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getWeigh() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getRelation() {
        return relation;
    }
}
