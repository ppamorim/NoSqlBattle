package com.nosqlbattle.model;

import io.realm.RealmObject;

public class UserRealm extends RealmObject {
    public String id;
    public int index;
    public String guid;
    public boolean isActive;
    public String balance;
    public String pictureUrl;
    public int age;
    public String company;
    public String email;
    public String address;
    public String about;
    public String registered;
    public double latitude;
    public double longitude;
    public String greeting;
    public String favoriteFruit;
    public String eyeColor;
    public String phone;
}
