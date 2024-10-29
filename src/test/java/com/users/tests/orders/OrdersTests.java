package com.users.tests.orders;

import com.users.requests.factory.OrdersRequestFactory;
import com.users.tags.Regression;
import com.users.tags.Smoke;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OrdersTests extends BaseClass {

    OrdersRequestFactory requestFactory = new OrdersRequestFactory();


    @Category({Regression.class})
    @Story("get Orders")
    @DisplayName("get Orders")
    @Feature("Orders")
    @Tag("Regression")
    @Test
    public void getOrders(){
        requestFactory.getOrders().then().log().all().statusCode(200);
    }

    @Category({Regression.class, Smoke.class})
    @Story("create Orders")
    @DisplayName("create Orders")
    @Feature("Orders")
    @Tag("Regression, Smoke")
    @Test
    public void addOrder(){
        String order ="{\"orderItems\":[{\"product\":6,\"name\":\"Amazon Echo Dot 3rd Generation\",\"image\":\"/images/alexa.jpg\",\"price\":\"29.99\",\"countInStock\":1,\"qty\":1}],\"shippingAddress\":{\"address\":\"Radnicka 38/46\",\"city\":\"Beograd\",\"postalCode\":\"11030\",\"country\":\"Serbia\"},\"paymentMethod\":\"PayPal\",\"itemsPrice\":\"29.99\",\"shippingPrice\":\"10.00\",\"taxPrice\":\"2.46\",\"totalPrice\":\"42.45\"}";
        requestFactory.addOrder(order).then().log().all().statusCode(200);
    }

    @Category({Regression.class, Smoke.class})
    @Story("get my  Orders")
    @DisplayName("get my Orders")
    @Feature("Orders")
    @Tag("Regression, Smoke")
    @Test
    public void getMyOrders(){
        requestFactory.getMyOrders().then().log().all().statusCode(200);
    }

    @Category({Regression.class, Smoke.class})
    @Story("Pay Order")
    @DisplayName("change order status to payed")
    @Feature("Orders")
    @Tag("Regression, Smoke")
    @Test
    public void updateOrdersToPayed(){
        String orderId ="33";
        requestFactory.updateOrderToPayed(orderId).then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("get order by id")
    @DisplayName("get order by id")
    @Feature("Orders")
    @Tag("Regression")
    @Test
    public void getOrderById(){
        String orderId ="12";
        requestFactory.getOrderById(orderId).then().log().all().statusCode(200);
    }

    @Category({Regression.class})
    @Story("update order to delivered")
    @DisplayName("update order to delivered")
    @Feature("Orders")
    @Tag("Regression")
    @Test
    public void updateOrderToDeliveredTest(){
        String orderId ="33";
        requestFactory.updateOrderToDelevired(orderId).then().log().all().statusCode(200);
    }
}
