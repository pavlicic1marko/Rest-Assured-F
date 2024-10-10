package com.users.specs;
import com.users.tests.TestBase;
import com.users.util.PropertyReader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Objects;


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

        if(Objects.equals(PropertyReader.getInstance().getProperty("log"), "True")){
            logBuilder.addFilter (new AllureRestAssured());
        }
        logSpecification = logBuilder.build();

        return logSpecification;

    }
}
