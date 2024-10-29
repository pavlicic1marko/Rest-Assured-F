package com.users.requests.factory;

import com.users.requests.RestClient;
import com.users.tests.BaseClass;
import io.restassured.response.Response;

public class OrdersRequestFactory extends BaseClass {

    RestClient restClient = new RestClient();

    public Response getOrders(){
        return restClient.doGetRequest("/orders/");
    }

    public Response getOrderById(String orderID) {
        return restClient.doGetRequest("/orders/" + orderID + "/");
    }

    public Response addOrder(String orderJson) {
        return restClient.doPostRequest(orderJson,"/orders/add/");
    }

    public Response getMyOrders(){
        return restClient.doGetRequest("/orders/myorders/");
    }

    public Response updateOrderToPayed(String orderId){
        return restClient.doPutRequest("/orders/" + orderId  + "/pay/","");
    }

    public Response updateOrderToDelevired(String orderId){
        return restClient.doPutRequest("/orders/" + orderId  + "/deliver/","");
    }



}
