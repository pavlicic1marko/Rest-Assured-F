package com.users.tests.Users;

import com.users.requests.factory.UserRequestFactory;
import com.users.tags.Regression;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UserPermissionsTests extends BaseClass {

    UserRequestFactory userRequestFactory = new UserRequestFactory();

    @Category({Regression.class})
    @Story("get user by Id")
    @DisplayName("try to get users with regular user credentials")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void getUsersById(){
        String userId = "1";
        String responseBody = userRequestFactory.getUsersByIdWithRegularCredentials(userId).then().log().all().statusCode(403).extract().response().body().asString();
        Assert.assertEquals("{\"detail\":\"You do not have permission to perform this action.\"}",responseBody);
    }


}
