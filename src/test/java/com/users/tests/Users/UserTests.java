package com.users.tests.Users;

import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.requests.factory.UserRequestFactory;
import com.users.specs.SpecificationFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;





import static com.users.util.JosnSerializer.serializeObjectToJson;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


public class UserTests extends BaseClass {

    UserRequestFactory requestFactory = new UserRequestFactory();

    @Category({Smoke.class, Regression.class})
    @Story("create user")
    @DisplayName("create user")
    @Feature("User")
    @Tag("Regression,Smoke")
    @Test
    public void createUser(){
        requestFactory.registerUserEshop().then().log().all();
    }

    @Category({Smoke.class, Regression.class})
    @Story("update user profile")
    @DisplayName("update user profile")
    @Feature("User")
    @Tag("Regression,Smoke")
    @Test
    public void updateUserProfile(){



        //create user, get token
        String user_token =  requestFactory.registerUserEshop().then().extract().path("token");

        //update user

        Faker faker = new Faker();
        String newName = faker.name().username();
        String newEmail = faker.name().username() + "updated@test.com";

        User user = new User();
        user.setEmail(newEmail);
        user.setName(newName);
        user.setPassword("Posao2018!");

        String jsonBody = serializeObjectToJson(user);

        requestFactory.updateUserProfile(user_token,  jsonBody)
                .then().log().all()
                .statusCode(200)
                .body("email",equalTo(newEmail),
                        "username",equalTo(newEmail)
                        ,"$",hasKey("_id")
                        ,"$",hasKey("id")
                        ,"$",hasKey("isAdmin")
                        ,"$",hasKey("token"));

    }

    @Category({Smoke.class, Regression.class})
    @Story("get user profile")
    @DisplayName("get user profile")
    @Feature("User")
    @Tag("Regression,Smoke")
    @Test
    public void getUserProfile(){
        requestFactory.getUserProfile().then().log().all().spec(SpecificationFactory.getStatusCode200());

    }

    @Category({Regression.class})
    @Story("get users")
    @DisplayName("get users")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void getUsers()
    {
        requestFactory.getUsers().then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("get user by Id")
    @DisplayName("get user by Id")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void getUsersById(){
        String userId = "10";
        requestFactory.getUsersById(userId).then().log().all().statusCode(200)
                .body(matchesJsonSchemaInClasspath("GetUserSchema"));

    }

    // update user vs update profile, you can change isAdmin, email, name
    @Category({Regression.class})
    @Story("update user")
    @DisplayName("update user")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void updateUser(){


        //create user
        String userId =  requestFactory.registerUserEshop().then().log().all().statusCode(200).extract().path("id").toString();
        System.out.println("user id is:" + userId);


        //update user, email and admin status
        Faker fakerApi = new Faker();
        String username = fakerApi.name().firstName();

        User user = new User();
        user.setName(username);
        user.setEmail(username + "updated@test.com");
        user.setIsAdmin(true);

        String jsonInString = serializeObjectToJson(user);

        requestFactory.updateUser(jsonInString, userId).then().log().all().statusCode(200);

    }

    @Category({Regression.class})
    @Story("delete user")
    @DisplayName("delete user")
    @Feature("User")
    @Tag("Regression")
    @Test
    public void deleteUser(){


        //create user
        String userId =  requestFactory.registerUserEshop().then().statusCode(200).extract().path("id").toString();
        System.out.println("user id is:" + userId);

        //delete user
        requestFactory.deleteUser(userId).then().log().all().statusCode(200);


    }
}
