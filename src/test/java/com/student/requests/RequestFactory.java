package com.student.requests;

import com.student.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestFactory extends TestBase {

    @Step("get all student information")
    public Response getAllUsers(){

        return RestAssured.given().header("Authorization", "Bearer "+ access_token)
                .when()
                .get("/users");
    }

    @Step("Register a student")
    public Response registerUser(){

        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("email", "testt1231@test.com")
                .multiPart("password", "12345678")
                .multiPart("name", "test")
                .when()
                .post("/users/register");
    }

}
