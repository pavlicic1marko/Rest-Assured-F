package com.users.tests.TodoService;

import io.restassured.RestAssured;
import org.junit.Test;

public class TodoTest {

    @Test
    public void getTodoTest(){

         String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI5MzY2OTA2LCJpYXQiOjE3Mjg5MzQ5MDYsImp0aSI6IjE1ZDc4NTRmYzRlMTQzMDNhOTU0MWMyOGZmN2U0ZmIyIiwidXNlcl9pZCI6MX0.tTUbpxCWfLmAQtPGGt1V8mOEo665jTl1xXdiGYWXHYE";

        RestAssured.given().header("Authorization","Bearer " + token).when().get("http://127.0.0.1:8001/api/todo/products").then().log().all();
    }
}
