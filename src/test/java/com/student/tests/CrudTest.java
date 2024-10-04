package com.student.tests;


import com.student.requests.RequestFactory;
import com.student.specs.SpecificationFactory;
import com.student.tags.Smoke;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Story("get routes for microservice")
public class CrudTest extends  TestBase{

    RequestFactory requestFactory = new RequestFactory();

    @Category(Smoke.class)
    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Test
    public void getAllUsers(){

        requestFactory.getAllUsers().then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().all().statusCode(200);
    }


    @Story("create a student")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Test
    public void createUser(){
        requestFactory.registerUser().then().log().all().statusCode(200);
    }
}







