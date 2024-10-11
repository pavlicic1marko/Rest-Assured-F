package com.users.tests;

import com.github.javafaker.Faker;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.users.requests.RequestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class AdvancedParametrizedTest extends TestBase{

    RequestFactory requestFactory = new RequestFactory();

    @DataProvider
    public static Object[][] userEmails(){
        Faker fakerApi = new Faker();

        return  new Object[][]{
              {fakerApi.name().fullName(),fakerApi.name().lastName(),fakerApi.name().nameWithMiddle()},
                {fakerApi.name().fullName(),fakerApi.name().lastName(),fakerApi.name().nameWithMiddle()},
                {fakerApi.name().fullName(),fakerApi.name().lastName(),fakerApi.name().nameWithMiddle()}
      };
    }

    @DataProvider
    public static Object[][] dataProviderAdd(){
        return  new Object[][]{
                {110,111},
        };
    }

    @UseDataProvider("dataProviderAdd")
    @Test

    public void firstTest(int num1, int num2){
        System.out.println(num1 +" OR "+ num2);
    }

    @UseDataProvider("userEmails")
    @Test
    public void registerUsers(String email, String firstName, String password){
        requestFactory.registerUser(email ,firstName ,password, false).then().log().all();
        System.out.println(email);
    }
    @Test
    public void secondTest(){
        System.out.println("this is second test");
    }
}
