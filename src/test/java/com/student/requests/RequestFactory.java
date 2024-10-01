package com.student.requests;

import com.github.javafaker.Faker;
import com.student.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestFactory extends TestBase {

    Faker fakerApi = new Faker();
    String firstName = fakerApi.name().firstName();
    String email = firstName + "@testing.com";


    @Step("get all student information")
    public Response getAllStudents(){


        return RestAssured.given().header("Authorization", "Bearer "+ token)
                .when()
                .get("/users");
    }

    @Step("Register a student")
    public Response postNewStudent(){

        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("email", email)
                .multiPart("password", "12345678")
                .multiPart("name", firstName)
                .when()
                .post("/users/register");
    }

}
