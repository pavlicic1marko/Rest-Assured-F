package com.student.tests;

import com.student.util.PropertyReader;
import io.restassured.RestAssured;
import org.junit.Before;

public class TestBase {

    public static PropertyReader prop;
    public String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI4MjA0MDA2LCJpYXQiOjE3Mjc3NzIwMDYsImp0aSI6IjAzOWE1NDY1NjQ2MzQ5NjI5ZDdkMWUxMThkMmQ0ZWY4IiwidXNlcl9pZCI6MX0.CU7a2mgNkA6NLHLvEuJpN7jcsXQFD5HYBMw_pgFfckk";


    @Before
    public void init(){
        prop = PropertyReader.getInstance();

        RestAssured.baseURI =prop.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(prop.getProperty("port"));
    }
}
