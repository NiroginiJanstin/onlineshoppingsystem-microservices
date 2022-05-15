package com.shopping.catalogservice.service;

import com.shopping.catalogservice.entity.Product;
import com.shopping.catalogservice.model.ProductResponseModel;

public interface IProductService {
    public ProductResponseModel create(Product product);
    public ProductResponseModel update(String productId, Product product);
    public ProductResponseModel getById(String productId);
    public ProductResponseModel delete(String productId);
}
