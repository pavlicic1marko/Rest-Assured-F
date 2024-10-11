package com.users.tests;


import com.github.javafaker.Faker;
import com.users.requests.RequestFactory;
import com.users.specs.SpecificationFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;


@Story("get routes for microservice")
public class UserTest extends  TestBase{

    RequestFactory requestFactory = new RequestFactory();

    @Category({Smoke.class, Regression.class})
    @Story("get routes for microservice")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression,Smoke")
    @Test
    public void getAllUsers(){

        requestFactory.getAllUsers().then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().all().statusCode(200);
    }
    @Category({Smoke.class, Regression.class})
    @Story("get user profile")
    @DisplayName("Test Name")
    @Feature("User Profile")
    @Tag("Regression,Smoke")
    @Test
    public void getUserProfile(){
        requestFactory.getUserProfile().then()
                .spec(SpecificationFactory.getGenericResponseSpec())
                .log().all().statusCode(200);
    }

    @Category(Regression.class)
    @Story("create a student")
    @DisplayName("create student and check values")
    @Feature("Feature name")
    @Tag("Regression")
    @Test
    public void createUser(){

        Faker fakerApi = new Faker();
        String firstName = fakerApi.name().firstName();
        String email = firstName + "@testing.com";
        Boolean isAdmin = false;

        requestFactory.registerUser(email, firstName, "12345678", isAdmin)
                .then()
                .body("email",equalTo(email)
                        , "username",equalTo(email)
                        ,"name",equalTo(firstName)
                        ,"isAdmin",equalTo(isAdmin)
                        ,"$",hasKey("_id")
                        ,"$",hasKey("id")
                        ,"$",hasKey("token"))
                .log().all().statusCode(200);
    }

    @Category(Regression.class)
    @Story("create a admin user")
    @DisplayName("create admin user and check values")
    @Feature("Feature name")
    @Tag("Regression")
    @Test
    public void createAdminUser(){

        Faker fakerApi = new Faker();
        String firstName = fakerApi.name().firstName();
        String email = firstName + "@testing.com";
        Boolean isAdmin = true;

        requestFactory.registerUser(email, firstName, "12345678", isAdmin)
                .then()
                .body("email",equalTo(email)
                        , "username",equalTo(email)
                        ,"name",equalTo(firstName)
                        ,"isAdmin",equalTo(1)
                        ,"$",hasKey("_id")
                        ,"$",hasKey("id")
                        ,"$",hasKey("token"))
                .log().all().statusCode(200);
    }


    @Category(Regression.class)
    @Story("delete user")
    @DisplayName("create  user and delete")
    @Feature("Feature name")
    @Tag("Regression")
    @Test
    public void createUserAndDeleteUser(){
        Faker fakerApi = new Faker();
        String firstName = fakerApi.name().firstName();
        String email = firstName + "@testing.com";
        Boolean isAdmin = true;
        int  createdUserId = requestFactory.registerUser(email, firstName, "12345678", isAdmin)
                .then().extract().path("id");

        System.out.println("this is the id:"+ createdUserId);

        requestFactory.deleteUserByUserName(email).then().statusCode(200).log().all();
    }

    @Category(Regression.class)
    @Story("create a student")
    @DisplayName("Test Name")
    @Feature("Feature name")
    @Tag("Regression")
    @Test
    public void loginAdminUser(){

        String token  = requestFactory.loginUser("dennis", "Posao2018!")
                .then()
                .body("$",hasKey("access"))
                .extract().path("access");

        System.out.println(token);
    }
    @Category(Regression.class)
    @Story("update a student")
    @DisplayName("Test Name")
    @Feature("Student update")
    @Tag("Regression")
    @Test
    public void updateUserData(){
        //create user and then update the email and username
        Faker fakerApi = new Faker();
        String firstName = fakerApi.name().firstName();
        String email = firstName + "@testing.com";
        Boolean isAdmin = true;

        String  Access_Token = requestFactory.registerUser(email, firstName, "12345678", isAdmin)
                .then().extract().path("token");

        String newEmail = firstName + "1@testing.com";

        requestFactory.updateStudent(newEmail, newEmail, Access_Token).then()
                .body("email",equalTo(newEmail),"username",equalTo(newEmail))
                .log().all();
    }
}









