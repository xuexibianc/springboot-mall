package com.fw.springbootmall.service;

import com.fw.springbootmall.dto.CreateOrderRequest;
import com.fw.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);


    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
