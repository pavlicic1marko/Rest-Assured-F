package com.users.requests;

import com.users.specs.SpecificationFactory;
import com.users.tests.ProductsBaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class RestClient extends ProductsBaseClass {



    public Response doGetRequest(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ access_token)
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .get(requestPath);
    }

    public Response doPutRequest(String path, Object body){

        return RestAssured.given().header("Authorization", "Bearer "+ getToken())
                .contentType(ContentType.JSON)
               //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(body)
                .put(path);
    }

    public Response doDeleteRequest(String path){

        return RestAssured
                .given()
                .header("Authorization","Bearer " + getToken())
                .when()
                .delete(path);
    }

    public Response doPatchRequest(String path, Object body){

        return RestAssured
                .given()
                .when()
                .body(body)
                .patch(path);
    }

    public Response doPostRequest(String body, String path){
        return RestAssured
                .given().header("Content-Type","application/json")
                .header("Authorization","Bearer " + getToken())
                .when()
                .body(body)
                .post(path);
    }
}
