package com.shopping.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shopping.customerservice.model.Customer;
import com.shopping.customerservice.repository.CustomerRepository;
import com.shopping.customerservice.service.CustomerService;

import java.util.List;

@RequestMapping("/api/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    CustomerRepository repo;

    @PostMapping("/save")
    public Customer addCustomer(@RequestBody Customer customer)
    {
    	Customer cus = repo.save(customer);
        return cus;
    }
    
    @GetMapping("/getAll")
    public List<Customer> listAll() {
        List<Customer> listUsers = repo.findAll();
        return listUsers;
    }
 
    @GetMapping(path = "/getById")
    public Customer getCustomer(@RequestParam("id") int id)
    {
    	Customer cus = repo.getById(id);
    	return cus;
    }

    @DeleteMapping(path = "/delete")
    public String deleteCustomer(@RequestParam("id") int id)
    {
    	repo.deleteById(id);
        return  "Customer Id " + id + " is Deleted!!";
    }
    
    @PutMapping(path = "/update")
    public Customer updateCustomer(@RequestBody Customer customer)
    {
    	Customer cus = new Customer();
    	cus.setCustomer_id(customer.getCustomer_id());
    	cus.setName(customer.getName());
    	cus.setPhone(customer.getPhone());
    	cus.setAddress(customer.getAddress());
    	cus.setCustomer_code(customer.getCustomer_code());
    	Customer cust = repo.save(cus);
    	return cust;
        
    }
    
 
}
