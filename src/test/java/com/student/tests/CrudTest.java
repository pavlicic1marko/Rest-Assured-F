package com.student.tests;


import io.restassured.RestAssured;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CrudTest {

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







