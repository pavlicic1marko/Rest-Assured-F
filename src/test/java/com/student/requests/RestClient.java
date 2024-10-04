package com.student.requests;

import com.student.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient extends TestBase {

    public Response doGetRequest(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ access_token)
                .when()
                .get(requestPath);
    }

    public Response doPostRequest(String path, Object body){

        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .body(body)
                .post(path);
    }
}
