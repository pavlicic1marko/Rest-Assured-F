package com.student.requests;

import com.student.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestFactory extends TestBase {

    @Step("get all student information")
    public Response getAllStudents(){

        return RestAssured.given()
                .when()
                .get("/routes");

    }
}
