package com.users.tests;

import com.users.requests.RequestFactory;
import com.users.requests.RestClient;
import com.users.tags.Regression;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CommsTest extends TestBase{


    RestClient restClient  = new RestClient();

    @Category(CommsTest.class)
    @Story("comm with todo microservice")
    @DisplayName("communicate with todo microservice")
    @Feature("Feature name")
    @Tag("Comms")
    public void commsTest(){
        restClient.doGetRequest("/comms").then().log().all().statusCode(200);

    }

}
