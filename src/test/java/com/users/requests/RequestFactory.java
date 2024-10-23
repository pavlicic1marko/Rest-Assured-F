package com.users.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.tests.ProductsBaseClass;
import io.qameta.allure.Step;
import io.restassured.response.Response;


import static com.users.constants.Constants.UrlConstants.*;


public class RequestFactory extends ProductsBaseClass {



    RestClient restClient = new RestClient();

//Products Requests
    @Step("create product")
    public Response createProduct(){
        return  restClient.doPostRequest("","/products/create/");
    }

    @Step("Upload product image")
    public Response uploadProductImage(){
        return restClient.doPostRequest("","");
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

// User Requests
    @Step("Register User")
    public Response registerUserEshop(){


        User user = new User();
        Faker fakerApi = new Faker();
        String username = fakerApi.name().firstName();

        user.setName(username);
        user.setPassword("Posao2018!");
        user.setEmail(username + "@test.com");
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString;
        try {
            jsonInString = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }


       return restClient.doPostRequest(jsonInString, REGISTER_URL);
    }

    public Response updateUserProfile(String user_token, String newEmail, String newPassword){
         String bodyString ="{\"name\":\"Marko Pavlicicc\",\"email\":\"" + newEmail + "\",\"password\":\"" + newPassword + "\"}";

        return restClient.doPutRequest("/users/profile/update/", bodyString);
    }

/*
    @Step("get all student information")
    public Response getAllUsers(){
        
        return restClient.doGetRequest(USER);

    }

    @Step("get all Routes")
    public Response getAllRotes(){

        return restClient.doGetRequest(ROUTES);

    }

    @Step("Register a student")
    public Response registerUser(String email, String firstName, String password, Boolean isAdminUser){


        String path;

        if (isAdminUser) {
            path =  REGISTER_ADMIN_URL;
        }else {
            path= REGISTER_URL;
        }


        return RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("email", email)
                .multiPart("password", password)
                .multiPart("name", firstName)
                .when()
                .post(path);
    }

    @Step("delete a user")
    public Response deleteUserByUserName(String email){
        return restClient.doDeleteRequest(email);
    }

    @Step("Register a student")
    public Response loginUser(String username, String password){
        return   RestAssured.given()
                .contentType("multipart/form-data")
                .multiPart("username", username)
                .multiPart("password", password)
                .when()
                .post(USER_LOGIN);
    }

    @Step("update student information with first name: {0}, email {1}")
    public Response updateStudent(String newName, String newEmail, String newToken) {

        String path = USER_UPDATE;
        StudentClass student = new StudentClass();
        student.setName(newName);
        student.setEmail(newEmail);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonInString = mapper.writeValueAsString(student);
            return restClient.doPutRequest(path, jsonInString, newToken);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }





    }

    public Response getUserProfile(){

        return restClient.doGetRequest(USER_PROFILE);

    }*/


}
