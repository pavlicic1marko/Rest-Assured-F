package com.users.tests.orders;

import com.users.requests.RequestFactory;
import com.users.tests.ProductsBaseClass;
import org.junit.Test;

public class OrdersTests extends ProductsBaseClass {

    RequestFactory requestFactory = new RequestFactory();


    @Test
    public void getOrders(){
        requestFactory.getOrders().then().log().all().statusCode(200);
    }

    @Test
    public void getOrdersItems(){
    }

    @Test
    public void getMyOrders(){
    }

    @Test
    public void updateOrdersToDelivered(){
    }

    @Test
    public void getOrderById(){
        String orderId ="12";
        requestFactory.getOrderById(orderId).then().log().all().statusCode(200);
    }

    @Test
    public void updateOrderToPaid(){
    }
}
