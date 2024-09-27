package com.student.tests;


import io.restassured.RestAssured;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CrudTest {

    @Test
    public void test(){

        RestAssured.baseURI ="http://localhost:8000/api/routes";


        RestAssured.given()
                .when()
                .get()
                .then()
                .log()
                .body()
                .statusCode(200);

    }
}







