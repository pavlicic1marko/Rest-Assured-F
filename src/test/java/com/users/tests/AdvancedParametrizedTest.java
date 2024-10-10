package com.users.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class AdvancedParametrizedTest {

    @DataProvider
    public static Object[][] dataProviderAdd(){
      return  new Object[][]{
              {0,1},{1,2},{3,4}
      };
    }

    @DataProvider
    public static Object[][] dataProviderAdd2(){
        return  new Object[][]{
                {110,111},
        };
    }

    @UseDataProvider("dataProviderAdd")
    @Test
    public void firstTest(int num1, int num2){
        System.out.println(num1 +" OR "+ num2);
    }

    @UseDataProvider("dataProviderAdd2")
    @Test
    public void firstTest2(int num1, int num2){
        System.out.println(num1 +" OR "+ num2);
    }
    @Test
    public void secondTest(){
        System.out.println("this is second test");
    }
}
