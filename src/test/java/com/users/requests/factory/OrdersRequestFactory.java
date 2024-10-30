package com.users.requests.factory;

import com.users.requests.RestClient;
import com.users.tests.BaseClass;
import io.restassured.response.Response;

public class OrdersRequestFactory extends BaseClass {

    RestClient restClient = new RestClient();

    public Response getOrders(){
        return restClient.doGetRequestWitAdminCredentials("/orders/");
    }

    public Response getOrderById(String orderID) {
        return restClient.doGetRequestWitAdminCredentials("/orders/" + orderID + "/");
    }

    public Response addOrder(String orderJson) {
        return restClient.doPostRequestWithAdminCredentials(orderJson,"/orders/add/");
    }

    public Response getMyOrders(){
        return restClient.doGetRequestWitAdminCredentials("/orders/myorders/");
    }

    public Response updateOrderToPayed(String orderId){
        return restClient.doPutRequestWithAdminCredentials("/orders/" + orderId  + "/pay/","");
    }

    public Response updateOrderToDelevired(String orderId){
        return restClient.doPutRequestWithAdminCredentials("/orders/" + orderId  + "/deliver/","");
    }



}
