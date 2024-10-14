package com.users.tests.TodoService;

import io.restassured.RestAssured;
import org.junit.Test;

public class TodoTest {

    protected String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI5MzY2OTA2LCJpYXQiOjE3Mjg5MzQ5MDYsImp0aSI6IjE1ZDc4NTRmYzRlMTQzMDNhOTU0MWMyOGZmN2U0ZmIyIiwidXNlcl9pZCI6MX0.tTUbpxCWfLmAQtPGGt1V8mOEo665jTl1xXdiGYWXHYE";
    private  String todoId = "40";

    @Test
    public void getTodoTest(){

        RestAssured.given().header("Authorization","Bearer " + token).when().get("http://127.0.0.1:8001/api/todo/products").then().log().all();
    }


    @Test
    public void getTodoById(){

        RestAssured.given().header("Authorization","Bearer " + token).when().get("http://127.0.0.1:8001/api/todo/products/" + todoId).then().log().all();

    }

    @Test
    public void CreateTodoAndDelete(){
        RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .body("{\n" +
                "    \"title\":\"create from Rest Assured\",\n" +
                "    \"description\" :\"created from Rest \"\n" +
                "}").post("http://127.0.0.1:8001/api/create" ).then().log().all();

        RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .delete("http://127.0.0.1:8001/api/delete/61")
                .then().log().all();



    }
}
