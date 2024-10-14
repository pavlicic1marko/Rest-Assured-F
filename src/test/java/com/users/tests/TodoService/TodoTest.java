package com.users.tests.TodoService;

import com.users.util.UserCredentialsReader;
import io.restassured.RestAssured;
import org.junit.Test;

import static org.hamcrest.Matchers.hasKey;

public class TodoTest {

    public static UserCredentialsReader credentialsProp;

    protected String token = getToken();

    @Test
    public void getTodos(){

        RestAssured.given().header("Authorization","Bearer " + token)
                .when().get("http://127.0.0.1:8001/api/todo/products")
                .then().log().all();
    }


    @Test
    public void getTodoById(){

        //create object
        String todoId = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .body("{\n" +
                        "    \"title\":\"create from Rest Assured\",\n" +
                        "    \"description\" :\"created from Rest \"\n" +
                        "}").post("http://127.0.0.1:8001/api/create" ).then()
                .body("$",hasKey("_id"))
                .extract().path("_id").toString();

        //get object
        RestAssured.given().header("Authorization","Bearer " + token)
                .when().get("http://127.0.0.1:8001/api/todo/products/" + todoId)
                .then().log().all()
                .statusCode(200);

    }

    @Test
    public void CreateTodoAndDelete(){

        //create
        String todoId = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .body("{\n" +
                "    \"title\":\"create from Rest Assured\",\n" +
                "    \"description\" :\"created from Rest \"\n" +
                "}").post("http://127.0.0.1:8001/api/create" ).then()
                .body("$",hasKey("_id"))
                .extract().path("_id").toString();

        //delete
        RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + token)
                .when()
                .delete("http://127.0.0.1:8001/api/delete/" + todoId)
                .then().log().all();

    }

    public String getToken(){

        credentialsProp = UserCredentialsReader.getInstance();


        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", credentialsProp.getProperty("userName"))
                .multiPart("password", credentialsProp.getProperty("password"))
                .when()
                .post("http://127.0.0.1:8000/api/users/login").then()
                .body("$",hasKey("access"))
                .extract().path("access");
    }
}
