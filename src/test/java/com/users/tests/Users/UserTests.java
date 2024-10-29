package com.users.tests.Users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.requests.factory.UserRequestFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import com.users.tests.ProductsBaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class UserTests extends ProductsBaseClass {

    UserRequestFactory requestFactory = new UserRequestFactory();

    @Category({Smoke.class, Regression.class})
    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression,Smoke")
    @Test
    public void createUser(){
        requestFactory.registerUserEshop().then().log().all();
    }

    @Test
    public void updateUserProfile(){

        Faker faker = new Faker();
        String newEmail = faker.name().username() + "@test.com";

        //create user
        String user_token =  requestFactory.registerUserEshop().then().extract().path("token");
        System.out.println("user token is:" + user_token);

        //update user
        requestFactory.updateUserProfile(user_token, newEmail, "12345678!").then().log().all();

    }

    @Test
    public void getUserProfile(){
        requestFactory.getUserProfile().then().statusCode(200).log().all();

    }

    @Test
    public void getUsers()
    {
        requestFactory.getUsers().then().log().all();
    }

    @Test
    public void getUsersById(){
        String userId = "1";
        requestFactory.getUsersById(userId).then().log().all();

    }

    // update user vs update profile, you can change isAdmin, email, name
    @Test
    public void updateUser(){


        //create user
        String userId =  requestFactory.registerUserEshop().then().extract().path("id").toString();
        System.out.println("user id is:" + userId);


        //update user, email and admin status
        User user = new User();
        Faker fakerApi = new Faker();
        String username = fakerApi.name().firstName();

        user.setName(username);
        user.setEmail(username + "updated@test.com");
        user.setIsAdmin(true);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
        requestFactory.updateUser(jsonInString, userId).then().log().all().statusCode(200);

    }

    @Test
    public void deleteUser(){


        //create user
        String userId =  requestFactory.registerUserEshop().then().extract().path("id").toString();
        System.out.println("user id is:" + userId);

        //delete user
        requestFactory.deleteUser(userId).then().statusCode(200).log().all();


    }
}
