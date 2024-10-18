package com.users.tests.param;

import com.github.javafaker.Faker;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.users.requests.RequestFactory;
import com.users.tags.Regression;
import com.users.tests.ProductsBaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class AdvancedParametrizedTest extends ProductsBaseClass {

    RequestFactory requestFactory = new RequestFactory();


    @DataProvider
    public static Object[][] userData(){
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
    @Category(Regression.class)
    @Story("create 3 non-Admin users")
    @DisplayName("Test Name")
    @Feature("create users")
    @Tag("Regression")
    @UseDataProvider("userData")
    @Test
    public void registerUsers(String email, String firstName, String password){
        System.out.println("this is second test");
    }


    @Test
    public void secondTest(){
        System.out.println("this is second test");
    }
}
