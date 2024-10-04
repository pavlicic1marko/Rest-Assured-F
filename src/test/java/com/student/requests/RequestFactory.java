package com.student.requests;

import com.github.javafaker.Faker;
import com.student.pojo.StudentClass;
import com.student.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class RequestFactory extends TestBase {

    Faker fakerApi = new Faker();
    String firstName = fakerApi.name().firstName();
    String email = firstName + "@testing.com";

    RestClient restClient = new RestClient();


    @Step("get all student information")
    public Response getAllUsers(){

        String requestPath = "/users";

        return restClient.doGetRequest(requestPath);

    }

    @Step("Register a student")
    public Response registerUser(){

        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("email", email)
                .multiPart("password", "12345678")
                .multiPart("name", firstName)
                .when()
                .post("/users/register");
    }

    @Step("update student information with first name: {0}, lastName : {1}, email: {2}, program: {3}, courses: {4}")
    public Response updateStudent(String firstName, String lastName, String email, String program, List<String> courses){

        String path = "/student/create";
        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgram(program);
        student.setCourse(courses);


        return restClient.doPostRequest(path, student);


    }

}