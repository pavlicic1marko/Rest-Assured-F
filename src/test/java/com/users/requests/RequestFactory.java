package com.users.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.pojo.StudentClass;
import com.users.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import static com.users.constants.Constants.UrlConstants.*;


public class RequestFactory extends TestBase {



    RestClient restClient = new RestClient();


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

    }


}
