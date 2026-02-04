package com.example.exampratice;

import com.google.gson.annotations.SerializedName;

public class StudentModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    // JSONPlaceholder has no "age" or "faculty" → use defaults or map
    private int age = 0; // default

    private String faculty = "N/A"; // default

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getFaculty() { return username != null ? username : faculty; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Faculty: " + getFaculty();
    }
}