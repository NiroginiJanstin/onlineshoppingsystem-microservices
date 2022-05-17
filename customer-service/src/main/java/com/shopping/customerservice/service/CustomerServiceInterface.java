package com.shopping.customerservice.service;

import java.util.List;
import com.shopping.customerservice.entity.Customer;
import com.shopping.customerservice.model.CustomerModel;

public interface CustomerServiceInterface {
	public CustomerModel create(Customer customer);
    public CustomerModel update(String customer_id, Customer customer);
    public CustomerModel getById(String customer_id);
    public CustomerModel delete(String customer_id);
    public List<String> getCustomerIds();
    public String deleteAll();
}
