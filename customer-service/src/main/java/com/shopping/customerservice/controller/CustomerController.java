package com.shopping.customerservice.controller;

import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopping.customerservice.entity.Customer;
import com.shopping.customerservice.model.CustomerModel;
import com.shopping.customerservice.service.CustomerServiceInterface;

@RestController
@RequestMapping("customer")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @GetMapping("/")
    public String customerController(){
        return "Welcome to customer controller class!";
    }

    @PostMapping("/save")
    public ResponseEntity<CustomerModel> createCustomer(@RequestBody Customer customer) {
        try{
            logger.info("Start calling customer service"+customer);
            return new ResponseEntity<>(customerServiceInterface.create(customer), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerModel> getCustomerById(@PathVariable("id") String customer_id) {
        try{
            return new ResponseEntity<>(customerServiceInterface.getById(customer_id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerModel> updateCustomer(@PathVariable("id") String customer_id, @RequestBody Customer customer) {
        try{
            return new ResponseEntity<>(customerServiceInterface.update(customer_id,customer), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerModel> deleteCustomer(@PathVariable("id") String customer_id) {
        try{
            return new ResponseEntity<>(customerServiceInterface.delete(customer_id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteCustomers() {
        try{
            return new ResponseEntity<>(customerServiceInterface.deleteAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
