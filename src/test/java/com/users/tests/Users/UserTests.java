package com.users.tests.Users;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.tests.ProdictsBaseClass;
import io.restassured.RestAssured;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;


public class UserTests extends ProdictsBaseClass {
    @Test
    public void createUser(){

        User user = new User();
        Faker fakerApi = new Faker();
        String username = fakerApi.name().firstName();

        user.setName(username);
        user.setPassword("Posao2018!");
        user.setEmail(username + "@test.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try {
             jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }


        RestAssured
                .given().header("Content-Type","application/json").body(jsonInString)
                .post("/users/register/")
                .then().log().all();


    }


}
