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
        RestAssured.port = 8001;
    }
}


