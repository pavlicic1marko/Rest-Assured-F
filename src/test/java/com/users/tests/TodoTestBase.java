package com.users.tests;
import com.users.util.PropertyReader;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;


public class TodoTestBase extends TestBase{

    public static PropertyReader prop;




    @Before
    public void init() {
        prop = PropertyReader.getInstance();

        RestAssured.baseURI = prop.getProperty("baseUrl");
        RestAssured.port = 8001;
    }
}


