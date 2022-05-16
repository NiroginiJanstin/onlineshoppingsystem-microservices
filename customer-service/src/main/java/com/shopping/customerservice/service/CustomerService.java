package com.shopping.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.customerservice.dao.CustomerDAO;
import com.shopping.customerservice.model.Customer;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public int addCustomer(Customer cus)
    {
       return customerDAO.addCustomer(cus);
    }

    public List<Customer> getAllCustomer()
    {
        return customerDAO.getAllCustomer();
    }

    public Customer getCustomer(int id)
    {
        return customerDAO.getCustomer(id);
    }

    public int deleteCustomer(int id)
    {
        return customerDAO.deleteCustomer(id);
    }
}
