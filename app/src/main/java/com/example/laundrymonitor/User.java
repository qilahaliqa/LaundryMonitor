package com.example.laundrymonitor;

public class User {
    private String Email, Username, Phone;

    public User(){

    }

    public User(String email, String username, String phone) {

        Email = email;
        Username = username;
        Phone = phone;

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}