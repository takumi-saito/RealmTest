package com.example.chicken.realmtest;

import android.content.Context;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by chicken on 2015/10/29.
 */
public class User extends RealmObject{

    @PrimaryKey
    private int id;
    private String Name;
    private int age;
    private String bloodType;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void setAge(int age) {

        this.age = age;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return age;
    }
    public String getBloodType() {
        return bloodType;
    }

}
