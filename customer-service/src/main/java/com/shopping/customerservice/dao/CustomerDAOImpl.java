package com.shopping.customerservice.dao;

import org.springframework.stereotype.Repository;
import com.shopping.customerservice.model.Customer;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    List<Customer> customer = new ArrayList<>();

    @Override
    public int addCustomer(Customer cus) {
    	customer.add(cus);
        return 1;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        for (Customer cus: customer) {
            if(cus.getCustomer_id() == id)
                return cus;
        }
        return null;
    }

    @Override
    public int deleteCustomer(int id) {

        Customer cusTODelete = null;

        for(Customer cus : customer)
        {
            if(cus.getCustomer_id() == id)
            	cusTODelete = cus;
        }

        if(cusTODelete !=null)
        {
        	customer.remove(cusTODelete);
            return  1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int updateCustomer(int id, Customer emp) {
        return 0;
    }
}
