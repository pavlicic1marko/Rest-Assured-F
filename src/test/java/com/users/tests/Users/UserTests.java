package com.users.tests.Users;

import com.github.javafaker.Faker;
import com.users.requests.RequestFactory;
import com.users.tests.ProductsBaseClass;
import org.junit.Test;


public class UserTests extends ProductsBaseClass {

    RequestFactory requestFactory = new RequestFactory();
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

    }

    @Test
    public void getUsers(){

    }

    @Test
    public void getUsersById(){

    }

    @Test
    public void updateUser(){

    }

    @Test
    public void deleteUser(){

    }
}
