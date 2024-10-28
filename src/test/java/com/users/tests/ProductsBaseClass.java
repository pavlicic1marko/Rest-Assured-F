package com.users.tests;

import com.users.util.PropertyReader;
import com.users.util.UserCredentialsReader;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import static org.hamcrest.Matchers.hasKey;

public class ProductsBaseClass {



    public static PropertyReader prop;
    public static UserCredentialsReader credentialsProp;
    public String access_token = getToken();

    public String getToken(){

        credentialsProp = UserCredentialsReader.getInstance();


        return RestAssured.given().header("Content-Type","application/json")
                    .body("{\"username\":\"Jerrellupdated@test.com\",\"password\":\"Posao2018!\"}")
                    .when()
                    .post("http://127.0.0.1:8000/api/users/login/").then().body("$",hasKey("access"))
                    .extract().path("token");

    }
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
