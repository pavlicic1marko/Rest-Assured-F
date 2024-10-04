package com.student.requests;

import com.student.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestClient extends TestBase {

    public Response doGetRequest(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ access_token)
                .when()
                .get(requestPath);
    }
}
