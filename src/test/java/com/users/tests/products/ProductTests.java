package com.users.tests.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.pojo.Product;
import com.users.pojo.Review;
import com.users.requests.factory.RequestFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class ProductTests extends BaseClass {

    RequestFactory requestFactory = new RequestFactory();
    /** creating second review should not be possible, negative testing*/
    @Category({Regression.class})
    @Story("crete second review")
    @DisplayName("create second reviewer")
    @Feature("review")
    @Tag("Regression")
    @Test
    public void createTwoReviewsBySameUser(){
        //create product
        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();

        //create first review
        String jsonInString;
        Review review = new Review();
        review.setComment("this is a test comment");
        review.setRating("5");

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(review);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        requestFactory.createProductReview(productId, jsonInString).then().statusCode(200).log().all();

        //try to create second review
        String jsonInStringReview2;
        Review reviewTwo = new Review();
        review.setComment("try to create comment 2");
        review.setRating("5");

        try {
            jsonInStringReview2 = mapper.writeValueAsString(reviewTwo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        requestFactory.createProductReview(productId, jsonInStringReview2).then().statusCode(400).log().all();
    }


    @Category({Regression.class})
    @Story("get all products")
    @DisplayName("get all products")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getAllProducts(){
        requestFactory.getAllProducts().then().log().all();
    }

    @Category({Regression.class})
    @Story("get  product by id")
    @DisplayName("get product by id")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getProductById(){
        String productId = "6";
        requestFactory.getProductById(productId).then().log().all();
    }

    @Category({Regression.class})
    @Story("get  product top")
    @DisplayName("get product top")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getTopProducts(){
        requestFactory.getTopProducts().then().log().all();
    }

    /**
     This method gets all products that match the search string
     */
    @Category({Regression.class})
    @Story("Search for product by keyword")
    @DisplayName("Search for product by keyword")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getProductsByKeyword(){
        String keyword = "mouse";
        String page= "1";
        requestFactory.getProductsByKeyword(keyword, page).then().log().all();
    }

    @Category({Regression.class})
    @Story("create product")
    @DisplayName("create product")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void createProduct(){
        requestFactory.createProduct().then().log().all();
    }

    @Category({Regression.class})
    @Story("create product")
    @DisplayName("upload product image")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void uploadImage(){

        int productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id");


        requestFactory.uploadProductImage(productId).then().log().all();
    }

    @Category({Regression.class, Smoke.class})
    @Story("create product review")
    @DisplayName("create product review")
    @Feature("Review")
    @Tag("Regression, Smoke")
    @Test
    public void createProductReview(){
        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();

        String jsonInString;
        Review review = new Review();
        review.setComment("this is a test comment");
        review.setRating("5");

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(review);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        requestFactory.createProductReview(productId, jsonInString).then().log().all();




    }

    @Category({Regression.class})
    @Story("update product")
    @DisplayName("update product")
    @Feature("Product")
    @Tag("Regression")
    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setName("new Name");
        product.setBrand("new Brand");
        product.setCategory("new Category");
        product.setDescription("new Description");

        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();

        String jsonInString;

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        requestFactory.updateProduct(productId, jsonInString).then().log().all();
    }

    @Category({Regression.class})
    @Story("delete product ")
    @DisplayName("delete product ")
    @Feature("Product")
    @Tag("Regression")
    @Test
    public void deleteProduct() {

        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();

        requestFactory.getProductById(productId).then().statusCode(200);

        requestFactory.deleteProduct(productId).then().log().all().statusCode(200);
    }
}
