package com.users.tests.TodoService;

import com.users.tags.Regression;
import com.users.tags.Todo;
import com.users.tests.TodoTestBase;
import com.users.util.Token;
import com.users.util.UserCredentialsReader;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.hasKey;

public class TodoTest extends TodoTestBase {
    public static Token token = new Token();

    protected String user_token = token.getToken();

    @Category(Todo.class)
    @Story("get todo")
    @DisplayName("Test Name")
    @Feature("Todo")
    @Tag("Todo")
    @Test
    public void getTodos(){

        RestAssured.given().header("Authorization","Bearer " + user_token)
                .when().get("/todo/products")
                .then().log().all();
    }

    @Category(Todo.class)
    @Story("get todo by id")
    @DisplayName("Test Name")
    @Feature("Todo")
    @Tag("Todo")
    @Test
    public void getTodoById(){

        //create object
        String todoId = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + user_token)
                .when()
                .body("{\n" +
                        "    \"title\":\"create from Rest Assured\",\n" +
                        "    \"description\" :\"created from Rest \"\n" +
                        "}").post("/create" ).then()
                .body("$",hasKey("_id"))
                .extract().path("_id").toString();

        //get object
        RestAssured.given().header("Authorization","Bearer " + user_token)
                .when().get("/todo/products/" + todoId)
                .then().log().all()
                .statusCode(200);

    }

    @Category(Todo.class)
    @Story("delete todo by id")
    @DisplayName("Test Name")
    @Feature("Todo")
    @Tag("Todo")
    @Test
    public void CreateTodoAndDelete(){

        //create
        String todoId = RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + user_token)
                .when()
                .body("{\n" +
                "    \"title\":\"create from Rest Assured\",\n" +
                "    \"description\" :\"created from Rest \"\n" +
                "}").post("/create" ).then()
                .body("$",hasKey("_id"))
                .extract().path("_id").toString();

        //delete
        RestAssured.given()
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + user_token)
                .when()
                .delete("/delete/" + todoId)
                .then().log().all();

    }

}
