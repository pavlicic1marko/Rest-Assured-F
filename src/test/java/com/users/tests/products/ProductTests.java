package com.users.tests.products;

import com.users.tests.ProductsBaseClass;
import io.restassured.RestAssured;
import org.junit.Test;


public class ProductTests extends ProductsBaseClass {

    @Test
    public void getAllProducts(){
        RestAssured.given().get("/products").then().log().all();
    }

    @Test
    public void getProductById(){
        RestAssured.given().get("/products/6/").then().log().all();
    }

    @Test
    public void getTopProducts(){
        RestAssured.given().get("/products/top/").then().log().all();
    }

    //TODO add comment
    @Test
    public void getProductsByKeyword(){
        String keyword = "mouse";
        String page= "1";
        RestAssured.given().get("/products/?keyword=" + keyword + "&page=" +page).then().log().all();
    }
}
