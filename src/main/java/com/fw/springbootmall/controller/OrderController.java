package com.fw.springbootmall.controller;

import com.fw.springbootmall.dto.CreateOrderRequest;
import com.fw.springbootmall.dto.OrderQueryParams;
import com.fw.springbootmall.model.Order;
import com.fw.springbootmall.service.OrderService;
import com.fw.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody  @Valid  CreateOrderRequest createOrderRequest){

      Integer orderId  = orderService.createOrder(userId,createOrderRequest);


      Order order = orderService.getOrderById(orderId);


      return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        // 取得 order list
        List<Order> orderList =  orderService.getOrders(orderQueryParams);

        // 取得 order 总数
        Integer count = orderService.countOrder(orderQueryParams);

        // 分页
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return  ResponseEntity.status(HttpStatus.OK).body(page);
    }

}
