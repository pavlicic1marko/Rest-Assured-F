package com.users.tests.Users;

import com.users.requests.RequestFactory;
import com.users.tests.ProductsBaseClass;
import org.junit.Test;


public class UserTests extends ProductsBaseClass {

    RequestFactory requestFactory = new RequestFactory();
    @Test
    public void createUser(){
        requestFactory.registerUserEshop().then().log().all();
    }


}
