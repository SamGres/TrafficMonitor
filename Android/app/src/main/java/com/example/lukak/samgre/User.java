package com.example.lukak.samgre;


import java.util.Set;

public class User {

    String phone_number;
    String password;
    String fullname;
    String email;


    public User() {     }

    public User(String phone_number, String password, String fullname, String email){
        this.phone_number = phone_number;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return fullname;
    }

    public void setName(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
