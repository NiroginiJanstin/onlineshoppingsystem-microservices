package com.shopping.customerservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.customerservice.utils.validation;
import com.shopping.customerservice.entity.Customer;
import com.shopping.customerservice.model.CustomerModel;
import com.shopping.customerservice.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerServiceInterface {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerModel create(Customer customer) {
    	CustomerModel responseModel = new CustomerModel();
        boolean customerNameError = validation.emptyFieldValidation(customer.getName());
        boolean customerCodeError = validation.emptyFieldValidation(customer.getCustomer_code());
        if (customerNameError) {
            responseModel.customer = null;
            responseModel.message = "Please provide customer name";
        } else if (customerCodeError) {
            responseModel.customer = null;
            responseModel.message = "Please provide customer code";
        } else {
            Customer _customer= new Customer(customer.getName(),customer.getPhone(),customer.getAddress(), customer.getCustomer_code());
            try {
            	Customer result = customerRepository.save(_customer);
                responseModel.customer = result;
                responseModel.message = "Successfully saved";
            } catch (Exception e) {
                logger.info("MongoDb Connection Error" + e);
                responseModel.customer = null;
                responseModel.message = "Try again, Error in connection established with Mongo DB";
            }
        }

        return responseModel;
    }

    public CustomerModel update(String customer_id, Customer cus) {
    	CustomerModel responseModel = new CustomerModel();
        Optional<Customer> customer = customerRepository.findById(customer_id);
        if (customer.isPresent()) {
        	Customer _customer = customer.get();
            if (cus.getName() != "") {
            	_customer.setName(cus.getName());
            }
            if (cus.getAddress() != "") {
            	_customer.setAddress(cus.getAddress());
            }
            if (cus.getCustomer_code() != "") {
            	_customer.setCustomer_code(cus.getCustomer_code());
            }
            if (cus.getPhone() != "") {
            	_customer.setPhone(cus.getPhone());
            }
            try {
                Customer result = customerRepository.save(_customer);
                responseModel.customer = result;
                responseModel.message = "Successfully Updated!";
            } catch (Exception e) {
                logger.info("MongoDb Connection Error" + e);
                responseModel.customer = null;
                responseModel.message = "Try again, Error in connection established with Mongo DB";
            }

        } else {
            responseModel.customer = null;
            responseModel.message = "No category found with provided Id!";
        }
        return responseModel;
    }

    public CustomerModel getById(String customer_id) {
    	CustomerModel responseModel = new CustomerModel();
        Optional<Customer> customer = customerRepository.findById(customer_id);
        if (customer.isPresent()) {
            responseModel.customer = customer.get();
            responseModel.message = "";
        } else {
            responseModel.customer = null;
            responseModel.message = "No customer found with provided Id!";
        }

        return responseModel;
    }

    @Override
    public CustomerModel delete(String customer_id) {
    	CustomerModel responseModel = new CustomerModel();
        try {
        	customerRepository.deleteById(customer_id);
            responseModel.customer = null;
            responseModel.message = "Deleted successfully!";
        } catch (Exception e) {
            logger.info("MongoDb Connection Error" + e);
            responseModel.customer = null;
            responseModel.message = "Try again, Error in connection established with Mongo DB";
        }
        return responseModel;
    }

    public List<String> getCustomerIds() {
        List<String> ids = new ArrayList<String>();
        customerRepository.findAll().forEach(customer -> ids.add(customer.getCustomer_id()));
        return ids;
    }

    public String deleteAll() {
    	customerRepository.deleteAll();
        return "All customers are deleted successfully!";
    }
}
