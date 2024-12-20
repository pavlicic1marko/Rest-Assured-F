package com.users.tests.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.pojo.Product;
import com.users.pojo.Review;
import com.users.requests.factory.ProductsRequestFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static com.users.util.JosnSerializer.serializeObjectToJson;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertTrue;


public class ProductTests extends BaseClass {

    ProductsRequestFactory productsRequestFactory = new ProductsRequestFactory();
    /** creating second review should not be possible, negative testing*/
    @Category({Regression.class})
    @Story("crete second review")
    @DisplayName("create second reviewer")
    @Feature("review")
    @Tag("Regression")
    @Test
    public void createTwoReviewsBySameUser(){
        //create product
        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

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
        productsRequestFactory.createProductReview(productId, jsonInString).then().log().all().statusCode(200);

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

        productsRequestFactory.createProductReview(productId, jsonInStringReview2).then().log().all().statusCode(400);
    }


    @Category({Regression.class})
    @Story("get all products")
    @DisplayName("get all products")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getAllProducts(){
        productsRequestFactory.getAllProducts().then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("get  product by id")
    @DisplayName("get product by id")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getProductById(){
        String productId = "40";
        productsRequestFactory.getProductById(productId).then().log().all().statusCode(200)
                .body(matchesJsonSchemaInClasspath("ProductSchema"));
    }

    @Category({Regression.class})
    @Story("get  product top")
    @DisplayName("get product top")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void getTopProducts(){
        productsRequestFactory.getTopProducts().then().log().all().statusCode(200);
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
        productsRequestFactory.getProductsByKeyword(keyword, page).then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("create product")
    @DisplayName("create product")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void createProduct(){
        productsRequestFactory.createProduct().then().log().all().statusCode(200);
    }

    @Category({Smoke.class})
    @Story("create product")
    @DisplayName("upload product image")
    @Feature("Products")
    @Tag("smoke")
    @Test
    public void uploadImage(){


        String imagePath="src/main/resources/sega-mega.jpg"; //image to upload

        //create product
        int productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id");

        //upload image
        productsRequestFactory.uploadProductImage(productId, imagePath).then().log().all().statusCode(200);

        //downloadImage and check
        String productIdWithImage = String.valueOf(productId);
        String imageUrl = productsRequestFactory.getProductById(productIdWithImage).then().log().all().statusCode(200).extract().path("image"); //get image url

        byte[] fileDownloaded =  productsRequestFactory.downloadProductImage(imageUrl);

        File inputFileImage = new File(imagePath);
        byte[] byteArrayOfLocalImage = new byte[(int) inputFileImage.length()];
        try (FileInputStream inputStream = new FileInputStream(inputFileImage)) {
            inputStream.read(byteArrayOfLocalImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(Arrays.equals(byteArrayOfLocalImage, fileDownloaded));


    }

    @Category({Regression.class, Smoke.class})
    @Story("create product review")
    @DisplayName("create product review")
    @Feature("Review")
    @Tag("Regression, Smoke")
    @Test
    public void createProductReview(){
        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

        Review review = new Review();
        review.setComment("this is a test comment");
        review.setRating("5");


        String jsonInString = serializeObjectToJson(review);



        productsRequestFactory.createProductReview(productId, jsonInString).then().log().all().statusCode(200);




    }

    @Category({Regression.class, Smoke.class})
    @Story("update product")
    @DisplayName("update product")
    @Feature("Product")
    @Tag("Regression, Smoke")
    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setName("new Name");
        product.setBrand("new Brand");
        product.setCategory("new Category");
        product.setDescription("new Description");

        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

        String jsonInString=serializeObjectToJson(product);



        productsRequestFactory.updateProduct(productId, jsonInString).then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("delete product ")
    @DisplayName("delete product ")
    @Feature("Product")
    @Tag("Regression")
    @Test
    public void deleteProduct() {

        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

        productsRequestFactory.getProductById(productId).then().log().all().statusCode(200);

        productsRequestFactory.deleteProduct(productId).then().log().all().statusCode(200);
    }
}
