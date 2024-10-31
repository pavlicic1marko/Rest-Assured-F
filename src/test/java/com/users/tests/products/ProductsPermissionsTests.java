package com.users.tests.products;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.pojo.Product;
import com.users.requests.factory.ProductsRequestFactory;
import com.users.tags.Regression;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProductsPermissionsTests extends BaseClass {

    ProductsRequestFactory productsRequestFactory = new ProductsRequestFactory();

    @Category({Regression.class})
    @Story("create product")
    @DisplayName("create product with regular credentials")
    @Feature("Products")
    @Tag("Regression")
    @Test
    public void createProduct(){
        productsRequestFactory.createProductWithRegularCredentials().then().log().all().statusCode(403);
    }

    @Test
    public void updateProduct(){
        Product product = new Product();
        product.setName("new Name");
        product.setBrand("new Brand");
        product.setCategory("new Category");
        product.setDescription("new Description");

        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

        String jsonInString;

        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        productsRequestFactory.updateProductWithRegularCredentials(productId, jsonInString).then().log().all().statusCode(403);
    }

    @Category({Regression.class})
    @Story("delete product ")
    @DisplayName("delete product with regular credentials")
    @Feature("Product")
    @Tag("Regression")
    @Test
    public void deleteProduct() {

        String productId = productsRequestFactory.createProduct().then().log().all().statusCode(200).extract().path("_id").toString();

        productsRequestFactory.deleteProductWithRegularCredentials(productId).then().log().all().statusCode(403);
    }
}
