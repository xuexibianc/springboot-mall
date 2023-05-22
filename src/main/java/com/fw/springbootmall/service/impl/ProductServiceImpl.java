package com.fw.springbootmall.service.impl;

import com.fw.springbootmall.dao.ProductDao;
import com.fw.springbootmall.model.Product;
import com.fw.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);

    }

}
