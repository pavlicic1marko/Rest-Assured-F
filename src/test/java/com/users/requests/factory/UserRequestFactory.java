package com.users.requests.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.users.pojo.User;
import com.users.requests.RestClient;
import com.users.tests.BaseClass;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.users.constants.Constants.UrlConstants.*;

public class UserRequestFactory extends BaseClass {

    RestClient restClient = new RestClient();

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


        return restClient.doPostRequestWithAdminCredentials(jsonInString, REGISTER_URL);
    }

    @Step("Update user profile")
    public Response updateUserProfile(String user_token, String jsonBody){


        return  RestAssured.given().header("Authorization", "Bearer "+ user_token)
                .contentType(ContentType.JSON)
                //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(jsonBody)
                .put(USER_PROFILE_UPDATE);
    }

    public Response getUserProfile(){
        return restClient.doGetRequestWitAdminCredentials(USER_PROFILE);
    }

    public Response getUsers(){
        return restClient.doGetRequestWitAdminCredentials(USER);
    }

    public Response getUsersWithRegularCredentials(){
        return restClient.doGetRequestWitRegularCredentials("/users/");
    }

    public Response getUsersById(String userId){
        return restClient.doGetRequestWitAdminCredentials(USER + userId + "/");
    }

    public Response getUsersByIdWithRegularCredentials(String userId){
        return restClient.doGetRequestWitRegularCredentials(USER + userId + "/");
    }

    public Response updateUser(String body, String userId){
        return restClient.doPutRequestWithAdminCredentials(USER_UPDATE + userId + "/", body);
    }

    public Response deleteUser(String userId){
        return  restClient.doDeleteRequestWithAdminCredentials(USER_DELETE + userId + "/");
    }

    public Response deleteUserWithRegularCredentials(String userId){
        return  restClient.doDeleteRequestWithRegularCredentials(USER_DELETE + userId + "/");
    }


}
