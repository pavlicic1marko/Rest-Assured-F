package com.student.specs;

import com.student.tests.TestBase;
import io.restassured.builder.ResponseSpecBuilder;
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
}
