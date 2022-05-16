package com.shopping.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shopping.customerservice.model.Customer;
import com.shopping.customerservice.service.CustomerService;

import java.util.List;

@RequestMapping("/api/employee")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public int addCustomer(@RequestBody Customer customer)
    {
        return customerService.addCustomer(customer);
    }
    @GetMapping("/emp")
    public List<Customer> getAllCustomer()
    {
        return customerService.getAllCustomer();
    }

    @GetMapping(path = "{id}")
    public Customer getCustomer(@PathVariable("id") int id)
    {
        return customerService.getCustomer(id);
    }

    @DeleteMapping(path = "{id}")
    public int deleteCustomer(@PathVariable("id") int id)
    {
        return  customerService.deleteCustomer(id);
    }
}
