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

import static com.users.constants.Constants.ErrorMessages.ERROR_403_MESSAGE;

//test endpoints without  admin permissions but with regular user credentials, expect 403
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
        Assert.assertEquals(ERROR_403_MESSAGE,responseBody);
    }

    @Category({Regression.class})
    @Story("get users")
    @DisplayName("get users with regular user credentials, 403")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void getUsers()
    {
        userRequestFactory.getUsersWithRegularCredentials().then().log().all().statusCode(403);
    }

    @Category({Regression.class})
    @Story("delete user")
    @DisplayName("try to delete user with regular credentials")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void deleteUser(){


        //create user
        String userId =  userRequestFactory.registerUserEshop().then().statusCode(200).extract().path("id").toString();
        System.out.println("user id is:" + userId);

        //delete user
        userRequestFactory.deleteUserWithRegularCredentials(userId).then().log().all().statusCode(403);


    }
}
