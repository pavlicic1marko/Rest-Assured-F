package com.users.requests.factory;

import com.users.requests.RestClient;
import com.users.tests.ProductsBaseClass;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class ProductsRequestFactory extends ProductsBaseClass {

    RestClient restClient = new RestClient();

    @Step("create product")
    public Response createProduct(){
        return  restClient.doPostRequest("","/products/create/");
    }

    @Step("Upload product image")
    public Response uploadProductImage(int productId){

        File inputFileImage = new File("src/main/resources/sega-mega.jpg");

        return RestAssured
                .given().contentType("multipart/form-data")
                .multiPart("product_id", productId)
                .multiPart("image", inputFileImage)
                .header("Authorization","Bearer " + getToken())
                .when()
                .post("/products/upload/");
    }

    @Step("Create product review")
    public Response createProductReview(String productId, String reviewJson){
        return restClient.doPostRequest(reviewJson,"/products/" + productId + "/reviews/");
    }

    @Step("update product")
    public Response updateProduct(String productId, String body){
        return restClient.doPutRequest("/products/update/" + productId + "/",body);
    }

    @Step("delete product")
    public Response deleteProduct(String id){

        return restClient.doDeleteRequest("/products/delete/" + id + "/");
    }


    @Step("get all products")
    public Response getAllProducts(){
        return restClient.doGetRequest("/products");

    }

    @Step("get product by ID")
    public Response getProductById(String productId){
        return restClient.doGetRequest("/products/" + productId);
    }

    @Step("get top products")
    public Response getTopProducts(){
        return restClient.doGetRequest("/products/top/");
    }

    @Step("get products by keywords")
    public Response getProductsByKeyword(String keyword, String page){
        return restClient.doGetRequest("/products/?keyword=" + keyword + "&page=" +page);
    }
}
