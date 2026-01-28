package com.example.dishdash.data.model.meals;

public class UserProfile {

    public String email;
    public String first_name;
    public String last_name;

    public UserProfile() {
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }
}
