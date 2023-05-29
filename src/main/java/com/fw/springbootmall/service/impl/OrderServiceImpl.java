package com.fw.springbootmall.service.impl;

import com.fw.springbootmall.dao.OrderDao;
import com.fw.springbootmall.dao.ProductDao;
import com.fw.springbootmall.dao.UserDao;
import com.fw.springbootmall.dto.BuyItem;
import com.fw.springbootmall.dto.CreateOrderRequest;
import com.fw.springbootmall.model.Order;
import com.fw.springbootmall.model.OrderItem;
import com.fw.springbootmall.model.Product;
import com.fw.springbootmall.model.User;
import com.fw.springbootmall.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        // 检查 user 是否存在
        User user =  userDao.getUserById(userId);

        if (user == null){
           log.warn("该 userId {} 不存在",userId);
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount = 0;

        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()){

            Product product = productDao.getProductById(buyItem.getProductId());

            // 检查 product 是否存在，库存是否足够
            if (product == null){
                log.warn("商品 {} 不存在",buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }else if (product.getStock() < buyItem.getQuantity()){
                log.warn("商品 {} 库存数量不足，无法购买剩余库存{},欲购买数量{}",
                        buyItem.getProductId(),product.getStock(),buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // 扣除商品库存
            productDao.updateStock(product.getProductId(),product.getStock() - buyItem.getQuantity());

            // 计算总价钱
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount = totalAmount + amount;

            // 转换 BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);

        }

        //创建订单
        Integer orderId = orderDao.createOrder(userId,totalAmount);

        orderDao.createOrderItems(orderId,orderItemList);

        return orderId;
    }
}
