package com.student.requests;

import com.student.pojo.StudentClass;
import com.student.tests.TestBase;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class RequestFactory extends TestBase {



    RestClient restClient = new RestClient();


    @Step("get all student information")
    public Response getAllUsers(){

        String requestPath = "/users";

        return restClient.doGetRequest(requestPath);

    }

    @Step("Register a student")
    public Response registerUser(String email, String firstName, String password, Boolean isAdminUser){

        String pathRegularUser ="/users/register";
        String pathAdminUser ="/users/register-admin";
        String path;

        if (isAdminUser) {
            path = pathAdminUser;
        }else {
            path= pathRegularUser;
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
                .post("/users/login");
    }

    @Step("update student information with first name: {0}, lastName : {1}, email: {2}, program: {3}, courses: {4}")
    public Response updateStudent(String firstName, String lastName, String email, String program, List<String> courses){

        String path = "/student/create";
        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgram(program);
        student.setCourse(courses);


        return restClient.doPostRequest(path, student);


    }

}
