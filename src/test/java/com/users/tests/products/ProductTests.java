package com.users.tests.products;

import io.restassured.RestAssured;
import org.junit.Test;

public class ProductTests {

    @Test
    public void getAllProducts(){
        RestAssured.given().get("http://127.0.0.1:8000/api/products").then().log().all();

    }
}
