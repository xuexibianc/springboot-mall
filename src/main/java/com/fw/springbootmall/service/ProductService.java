package com.fw.springbootmall.service;

import com.fw.springbootmall.constant.ProductCategory;
import com.fw.springbootmall.dto.ProductQueryParams;
import com.fw.springbootmall.dto.ProductRequest;
import com.fw.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId,ProductRequest productRequest);

    void deleteProductById(Integer productId);



}
