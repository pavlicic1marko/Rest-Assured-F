package com.users.tests.Users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.requests.RequestFactory;
import com.users.tests.ProdictsBaseClass;
import io.restassured.RestAssured;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserTests extends ProdictsBaseClass {

    RequestFactory requestFactory = new RequestFactory();
    @Test
    public void createUser(){
        requestFactory.registerUserEshop().then().log().all();
    }


}
