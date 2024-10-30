package com.users.requests.factory;

import com.users.requests.RestClient;
import com.users.tests.BaseClass;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class ProductsRequestFactory extends BaseClass {

    RestClient restClient = new RestClient();

    @Step("create product")
    public Response createProduct(){
        return  restClient.doPostRequest("","/products/create/");
    }

    @Step("Upload product image")
    public Response uploadProductImage(int productId, String imagePathLocation){

        File inputFileImage = new File(imagePathLocation);

        return RestAssured
                .given().contentType("multipart/form-data")
                .multiPart("product_id", productId)
                .multiPart("image", inputFileImage)
                .header("Authorization","Bearer " + getToken())
                .when()
                .post("/products/upload/");
    }
    @Step("Download product image")
    public byte[] downloadProductImage(String imageUrl){
        return restClient.doGetRequest("http://127.0.0.1:8000" + imageUrl)
                .then().log().headers()
                .statusCode(200)
                .extract().body().asByteArray();
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
