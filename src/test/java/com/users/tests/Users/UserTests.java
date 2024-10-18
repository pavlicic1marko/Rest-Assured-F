package com.users.tests.Users;

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
    public void updateUser(){

        //create user
        String user_token =  requestFactory.registerUserEshop().then().extract().path("token");
        System.out.println("user token is:" + user_token);

        //update user
        requestFactory.updateUserProfile(user_token).then().log().all();

    }

}
