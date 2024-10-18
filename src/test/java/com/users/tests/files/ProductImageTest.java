package com.users.tests.files;

import com.users.requests.RequestFactory;
import io.restassured.RestAssured;
import org.junit.Test;

public class ProductImageTest {

    RequestFactory requestFactory = new RequestFactory();

    @Test
    public void getProductImage(){

        RestAssured.given()
                .get("http://127.0.0.1:8000/images/alexa.jpg")
                .then().statusCode(200);

    }
}
