package com.student.specs;
import com.student.tests.TestBase;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class SpecificationFactory extends TestBase {


    public static synchronized ResponseSpecification getGenericResponseSpec(){

        ResponseSpecBuilder responseSpec;
        ResponseSpecification responseSpecification;

        responseSpec = new ResponseSpecBuilder();
        responseSpec.expectHeader("Content-Type","application/json");

        responseSpecification = responseSpec.build();

        return responseSpecification;
    }


    public static synchronized RequestSpecification logPayloadResponseInfo(){
        RequestSpecBuilder logBuilder;
        RequestSpecification logSpecification;

        logBuilder = new RequestSpecBuilder();
        logBuilder.addFilter (new AllureRestAssured());

        logSpecification = logBuilder.build();
        return logSpecification;

    }
}
