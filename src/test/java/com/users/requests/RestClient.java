package com.users.requests;

import com.users.specs.SpecificationFactory;
import com.users.tests.BaseClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class RestClient extends BaseClass {



    public Response doGetRequestWitAdminCredentials(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ getAdminToken())
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .get(requestPath);
    }

    public Response doGetRequestWitRegularCredentials(String requestPath){

        return RestAssured.given().header("Authorization", "Bearer "+ getToken())
                .spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .get(requestPath);
    }

    public Response doPutRequestWithAdminCredentials(String path, Object body){

        return RestAssured.given().header("Authorization", "Bearer "+ getAdminToken())
                .contentType(ContentType.JSON)
               //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(body)
                .put(path);
    }

    public Response doPutRequestWithRegularCredentials(String path, Object body){

        return RestAssured.given().header("Authorization", "Bearer "+ getToken())
                .contentType(ContentType.JSON)
                //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(body)
                .put(path);
    }

    public Response doDeleteRequestWithAdminCredentials(String path){

        return RestAssured
                .given()
                .header("Authorization","Bearer " + getAdminToken())
                .when()
                .delete(path);
    }

    public Response doDeleteRequestWithRegularCredentials(String path){

        return RestAssured
                .given()
                .header("Authorization","Bearer " + getToken())
                .when()
                .delete(path);
    }


    public Response doPostRequestWithAdminCredentials(String body, String path){
        return RestAssured
                .given().header("Content-Type","application/json")
                .header("Authorization","Bearer " + getAdminToken())
                .when()
                .body(body)
                .post(path);
    }

    public Response doPostRequestWithRegularCredentials(String body, String path){
        return RestAssured
                .given().header("Content-Type","application/json")
                .header("Authorization","Bearer " + getToken())
                .when()
                .body(body)
                .post(path);
    }
}
