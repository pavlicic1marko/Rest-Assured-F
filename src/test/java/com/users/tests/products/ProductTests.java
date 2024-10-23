package com.users.tests.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.pojo.Product;
import com.users.requests.RequestFactory;
import com.users.tests.ProductsBaseClass;
import org.junit.Test;


public class ProductTests extends ProductsBaseClass {

    RequestFactory requestFactory = new RequestFactory();


    @Test
    public void getAllProducts(){
        requestFactory.getAllProducts().then().log().all();
    }

    @Test
    public void getProductById(){
        String productId = "6";
        requestFactory.getProductById(productId).then().log().all();
    }

    @Test
    public void getTopProducts(){
        requestFactory.getTopProducts().then().log().all();
    }

    /**
     This method gets all products that match the search string
     */
    @Test
    public void getProductsByKeyword(){
        String keyword = "mouse";
        String page= "1";
        requestFactory.getProductsByKeyword(keyword, page).then().log().all();
    }

    @Test
    public void createProduct(){
        requestFactory.createProduct().then().log().all();
    }

    @Test
    public void uploadImage(){
    }

    @Test
    public void createProductReview(){
    }

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

    @Test
    public void deleteProduct() {

        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();

        requestFactory.getProductById(productId).then().statusCode(200);

        requestFactory.deleteProduct(productId).then().log().all().statusCode(200);
    }
}
