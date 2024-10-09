package com.student.tests;

import com.student.util.PropertyReader;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class TestBase {

    public static PropertyReader prop;
    public String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNzI4OTI1MDkzLCJpYXQiOjE3Mjg0OTMwOTMsImp0aSI6IjgyYzZlODY4YTBhNTQ1OTc5MGVjZmJlZDAzYTIwZmRlIiwidXNlcl9pZCI6MX0.-UZBkGQeEK3-oCFS8stiiMI_F9twFtkIE8rXHj24WuY";

    @Rule
    public TestRule listener = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("---------------------------------------------------------");
            System.out.println(description.getMethodName() + " test ended successfully");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("---------------------------------------------------------");
            System.out.println(description.getMethodName() + " test ended failed");
        }

        @Override
        protected void starting(Description description) {
            System.out.println("---------------------------------------------------------");
            System.out.println(description.getMethodName() + " test has started");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("---------------------------------------------------------");
            System.out.println(description.getMethodName() + " test has ended");
        }


    };

    @Before
    public void init() {
        prop = PropertyReader.getInstance();

        RestAssured.baseURI = prop.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(prop.getProperty("port"));
    }
}
