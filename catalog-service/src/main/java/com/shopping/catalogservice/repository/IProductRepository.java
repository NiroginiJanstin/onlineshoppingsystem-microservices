package com.shopping.catalogservice.repository;


import com.shopping.catalogservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository extends MongoRepository<Product,String> {

}
