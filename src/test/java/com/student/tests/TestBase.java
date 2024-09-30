package com.student.tests;

import io.restassured.RestAssured;
import org.junit.Before;

public class TestBase {

    @Before
    public void init(){

        RestAssured.baseURI ="http://localhost/api";
        RestAssured.port = 8000;
    }
}
