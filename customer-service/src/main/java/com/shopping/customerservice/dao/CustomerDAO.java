package com.shopping.customerservice.dao;

import java.util.List;

import com.shopping.customerservice.model.Customer;

public interface CustomerDAO {

    int addCustomer(Customer emp);

    List<Customer> getAllCustomer();

    Customer getCustomer(int id);

    int deleteCustomer(int id);

    int updateCustomer(int id, Customer emp);
}
