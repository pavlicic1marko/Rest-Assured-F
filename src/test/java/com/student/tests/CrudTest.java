package com.student.tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
@Story("get routes for microservice")
public class CrudTest {

    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Test
    public void test(){

        RestAssured.baseURI ="http://localhost/api";
        RestAssured.port = 8000;


        RestAssured.given()
                .when()
                .get("/routes")
                .then()
                .log()
                .body()
                .statusCode(200);

    }
}







