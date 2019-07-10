package com.example.test10;

public class Custom {
    int id;
    String name;
    String photo;
    String date;
    String phone;

    // 생성자
    Custom(){}
    Custom(String name, String photo, String date, String phone){
        this.name = name;
        this.photo = photo;
        this.date = date;
        this.phone = phone;
    }
}