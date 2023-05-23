package com.fw.springbootmall.dao;

import com.fw.springbootmall.dto.ProductRequest;
import com.fw.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
