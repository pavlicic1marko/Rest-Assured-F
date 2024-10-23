package com.users.tests.products;

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
    }

    @Test
    public void deleteProduct() {

        String productId = requestFactory.createProduct().then().statusCode(200).log().all().extract().path("_id").toString();


        requestFactory.deleteProduct(productId).then().log().all().statusCode(200);
    }
}
