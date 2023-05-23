package com.fw.springbootmall.service;

import com.fw.springbootmall.dto.ProductRequest;
import com.fw.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);



}
