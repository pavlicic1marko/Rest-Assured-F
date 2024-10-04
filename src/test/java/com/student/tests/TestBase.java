package com.student.tests;

import com.student.util.PropertyReader;
import io.restassured.RestAssured;
import org.junit.Before;

public class TestBase {

    public static PropertyReader prop;
    public String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI4NDU5NTQxLCJpYXQiOjE3MjgwMjc1NDEsImp0aSI6ImY3OGY3Mjc0MDU3OTQ5NTY4MGJhZTJlNTZhOTRkOTY2IiwidXNlcl9pZCI6MX0.-iHRIueMLTSTDFCBdGWGwmod80vt-zhCErofgWhAGwA";


    @Before
    public void init(){
        prop = PropertyReader.getInstance();

        RestAssured.baseURI =prop.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(prop.getProperty("port"));
    }
}
