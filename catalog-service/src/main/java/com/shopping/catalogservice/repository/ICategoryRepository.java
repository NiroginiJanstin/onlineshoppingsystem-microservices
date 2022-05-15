package com.shopping.catalogservice.repository;

import com.shopping.catalogservice.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category,String> {

}
