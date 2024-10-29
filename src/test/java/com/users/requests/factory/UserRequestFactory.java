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

import static com.users.constants.Constants.UrlConstants.REGISTER_URL;

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


        return restClient.doPostRequest(jsonInString, REGISTER_URL);
    }

    public Response updateUserProfile(String user_token, String newEmail, String newPassword, String newName){
        String bodyString ="{\"name\":\""+ newName +"\",\"email\":\"" + newEmail + "\",\"password\":\"" + newPassword + "\"}";


        return  RestAssured.given().header("Authorization", "Bearer "+ user_token)
                .contentType(ContentType.JSON)
                //.spec(SpecificationFactory.logPayloadResponseInfo())
                .when()
                .body(bodyString)
                .put("/users/profile/update/");
    }

    public Response getUserProfile(){
        return restClient.doGetRequest("/users/profile/");
    }

    public Response getUsers(){
        return restClient.doGetRequest("/users/");
    }

    public Response getUsersById(String userId){
        return restClient.doGetRequest("/users/" + userId + "/");
    }

    public Response updateUser(String body, String userId){
        return restClient.doPutRequest("/users/update/" + userId + "/", body);
    }

    public Response deleteUser(String userId){
        return  restClient.doDeleteRequest("/users/delete/" + userId + "/");
    }



}
