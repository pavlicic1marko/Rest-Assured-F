package com.users.tests.orders;

import com.github.javafaker.Faker;
import com.users.pojo.Product;
import com.users.pojo.ShippingAddress;
import com.users.pojo.Order;
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

import static com.users.util.JosnSerializer.serializeObjectToJson;
import static org.hamcrest.Matchers.hasKey;

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
        Order orders = new  Order();
        Product product = new Product();
        ShippingAddress address = new ShippingAddress();
        Faker fakerApi = new Faker();


        product.setName("test name");
        product.setPrice("10");
        product.setName("Amazon Echo Dot 3rd Generation");
        product.setProduct(1);
        product.setQty(1);


        address.setCity(fakerApi.address().city());
        address.setCountry(fakerApi.address().country());
        address.setPostalCode(fakerApi.address().zipCode());
        address.setAddress(fakerApi.address().streetAddress());

        orders.setShippingAddress(address);
        orders.setOrderItems(product);
        orders.setPaymentMethod("PayPal");
        orders.setShippingPrice("1");
        orders.setItemsPrice("1");
        orders.setTaxPrice("1");
        orders.setTotalPrice("1");
        orders.setCountInStock(1);

        String orderJson = serializeObjectToJson(orders);

        String order ="{\"orderItems\":[{\"product\":6,\"name\":\"Amazon Echo Dot 3rd Generation\",\"image\":\"/images/alexa.jpg\",\"price\":\"29.99\",\"countInStock\":1,\"qty\":1}],\"shippingAddress\":{\"address\":\"Radnicka 38/46\",\"city\":\"Beograd\",\"postalCode\":\"11030\",\"country\":\"Serbia\"},\"paymentMethod\":\"PayPal\",\"itemsPrice\":\"29.99\",\"shippingPrice\":\"10.00\",\"taxPrice\":\"2.46\",\"totalPrice\":\"42.45\"}";
        requestFactory.addOrder(orderJson).then().log().all().statusCode(200);
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

        String order ="{\"orderItems\":[{\"product\":6,\"name\":\"Amazon Echo Dot 3rd Generation\",\"image\":\"/images/alexa.jpg\",\"price\":\"29.99\",\"countInStock\":1,\"qty\":1}],\"shippingAddress\":{\"address\":\"Radnicka 38/46\",\"city\":\"Beograd\",\"postalCode\":\"11030\",\"country\":\"Serbia\"},\"paymentMethod\":\"PayPal\",\"itemsPrice\":\"29.99\",\"shippingPrice\":\"10.00\",\"taxPrice\":\"2.46\",\"totalPrice\":\"42.45\"}";
        String orderId = requestFactory.addOrder(order).then().log().all().statusCode(200).extract().path("_id").toString();

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
        requestFactory.getOrderById(orderId).then().log().all().statusCode(200).body(
                "$",hasKey("_id")
                ,"$",hasKey("name")
                ,"$",hasKey("qty")
                ,"$",hasKey("price")
                ,"$",hasKey("image"));;
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
