package com.users.tests.orders;

import com.users.requests.factory.OrdersRequestFactory;
import com.users.tests.BaseClass;
import org.junit.Test;

public class OrdersTests extends BaseClass {

    OrdersRequestFactory requestFactory = new OrdersRequestFactory();


    @Test
    public void getOrders(){
        requestFactory.getOrders().then().log().all().statusCode(200);
    }

    @Test
    public void addOrder(){
        String order ="{\"orderItems\":[{\"product\":6,\"name\":\"Amazon Echo Dot 3rd Generation\",\"image\":\"/images/alexa.jpg\",\"price\":\"29.99\",\"countInStock\":1,\"qty\":1}],\"shippingAddress\":{\"address\":\"Radnicka 38/46\",\"city\":\"Beograd\",\"postalCode\":\"11030\",\"country\":\"Serbia\"},\"paymentMethod\":\"PayPal\",\"itemsPrice\":\"29.99\",\"shippingPrice\":\"10.00\",\"taxPrice\":\"2.46\",\"totalPrice\":\"42.45\"}";
        requestFactory.addOrder(order).then().log().all();
    }

    @Test
    public void getMyOrders(){
        requestFactory.getMyOrders().then().log().all().statusCode(200);
    }

    @Test
    public void updateOrdersToPayed(){
        String orderId ="33";
        requestFactory.updateOrderToPayed(orderId).then().log().all().statusCode(200);
    }

    @Test
    public void getOrderById(){
        String orderId ="12";
        requestFactory.getOrderById(orderId).then().log().all().statusCode(200);
    }

    @Test
    public void updateOrderToDeliveredTest(){
        String orderId ="33";
        requestFactory.updateOrderToDelevired(orderId).then().log().all().statusCode(200);
    }
}
