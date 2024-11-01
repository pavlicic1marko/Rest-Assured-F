package com.users.tests.orders;

import com.users.requests.factory.OrdersRequestFactory;
import com.users.specs.SpecificationFactory;
import com.users.tags.Regression;
import com.users.tests.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OrdersPermissionsTests extends BaseClass {

    OrdersRequestFactory ordersRequestFactory = new OrdersRequestFactory();

    @Category({Regression.class})
    @Story("get Orders")
    @DisplayName("get Orders with regular user")
    @Feature("Orders")
    @Tag("Regression")
    @Test
    public void getOrders(){
        ordersRequestFactory.getOrdersWithRegularCredentials().then().log().all().spec(SpecificationFactory.getStatusCode403());
    }

    @Category({Regression.class})
    @Story("update order to delivered")
    @DisplayName("update order to delivered with regular credentials")
    @Feature("Orders")
    @Tag("Regression")
    @Test
    public void updateOrderToDeliveredTest(){
        String orderId ="33";
        ordersRequestFactory.updateOrderToDeleviredWithRegularCredentials(orderId).then().log().all().statusCode(403);
    }
}
