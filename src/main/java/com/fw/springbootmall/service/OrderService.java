package com.fw.springbootmall.service;

import com.fw.springbootmall.dto.CreateOrderRequest;
import com.fw.springbootmall.dto.OrderQueryParams;
import com.fw.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);


    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
