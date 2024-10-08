package com.student.requests;

import com.student.specs.SpecificationFactory;
import com.student.tests.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.student.constants.Constants.UrlConstants.USER_CREATE;
import static com.student.constants.Constants.UrlConstants.USER_DELETE;

public class RestClient extends TestBase {

    //RequestFactory requestFactory = new RequestFactory();


    public Response doGetRequest(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ access_token)
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .get(requestPath);
    }

    public Response doPostRequest(String path, Object body){

        return RestAssured.given()
                .contentType(ContentType.JSON)
               //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(body)
                .post(path);
    }

    public Response doDeleteRequest(String userEmail){

        return RestAssured
                .given()
                .when()
                .delete(USER_DELETE + userEmail);
    }

    public Response doPatchRequest(String path, Object body){

        return RestAssured
                .given()
                .when()
                .body(body)
                .patch(path);
    }
}
