package com.users.tests.products;

import com.users.tests.TodoService.ProdictsBaseClass;
import io.restassured.RestAssured;
import org.junit.Test;


public class ProductTests extends ProdictsBaseClass {

    @Test
    public void getAllProducts(){
        RestAssured.given().get("/products").then().log().all();

    }
}
