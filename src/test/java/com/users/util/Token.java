package com.users.util;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.hasKey;

public class Token {

    public static UserCredentialsReader credentialsProp;

    public  String getToken(){

        credentialsProp = UserCredentialsReader.getInstance();


        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", credentialsProp.getProperty("userName"))
                .multiPart("password", credentialsProp.getProperty("password"))
                .when()
                .post("http://127.0.0.1:8000/api/users/login").then()
                .body("$",hasKey("access"))
                .extract().path("access");
    }

    public  String getTokenForAdminUser(){

        credentialsProp = UserCredentialsReader.getInstance();


        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", credentialsProp.getProperty("AdminUserName"))
                .multiPart("password", credentialsProp.getProperty("AdminPassword"))
                .when()
                .post("http://127.0.0.1:8000/api/users/login").then()
                .body("$",hasKey("access"))
                .extract().path("access");
    }
}
