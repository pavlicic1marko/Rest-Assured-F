package com.student.tests;

import com.student.util.PropertyReader;
import io.restassured.RestAssured;
import org.junit.Before;

public class TestBase {

    public static PropertyReader prop;


    @Before
    public void init(){
        prop = PropertyReader.getInstance();

        RestAssured.baseURI =prop.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(prop.getProperty("port"));
    }
}
