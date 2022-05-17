package com.shopping.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.shopping.customerservice.entity.Customer;

public interface CustomerRepository extends MongoRepository<Customer,String> {

}