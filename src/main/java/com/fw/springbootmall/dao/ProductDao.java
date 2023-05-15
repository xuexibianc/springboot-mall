package com.fw.springbootmall.dao;

import com.fw.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
