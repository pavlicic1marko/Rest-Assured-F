package com.users.tests;

import com.users.requests.RequestFactory;
import com.users.specs.SpecificationFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.equalTo;


public class RoutesTest extends  UserTestBase{

    RequestFactory requestFactory = new RequestFactory();

    @Category({Smoke.class, Regression.class})
    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression,Smoke")
    @Test
    public void routesTest(
    ){
        requestFactory.getAllRotes().then().spec(SpecificationFactory.getGenericResponseSpec())
                .log().all()
                .body(equalTo("[\"api/products\",\"api/products/<id>\"]")) //check the response body is equal to string
                .statusCode(200);
    }
}
