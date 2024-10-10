package com.users.pojo;

import java.util.List;

public class StudentClass {

    String firstName;
    String lastName;
    String email;
    String program;
    List<String> course;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse( List<String> course) {
        this.course = course;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
