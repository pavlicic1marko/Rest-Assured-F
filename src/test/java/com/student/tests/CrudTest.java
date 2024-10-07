package com.student.tests;


import com.student.requests.RequestFactory;
import com.student.specs.SpecificationFactory;
import com.student.tags.Regression;
import com.student.tags.Smoke;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.hasKey;


@Story("get routes for microservice")
public class CrudTest extends  TestBase{

    RequestFactory requestFactory = new RequestFactory();

    @Category({Smoke.class, Regression.class})
    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression,Smoke")
    @Test
    public void getAllUsers(){

        requestFactory.getAllUsers().then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().all().statusCode(200);
    }

    @Category(Regression.class)

    @Story("create a student")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression")
    @Test
    public void createUser(){
        requestFactory.registerUser().then().log().all().statusCode(200);
    }

    @Test
    public void loginAdminUser(){

        requestFactory.loginUser("dennis", "Posao2018")
                .then()
                .body("$",hasKey("access"))
                 .log().all().statusCode(200);
    }
}









