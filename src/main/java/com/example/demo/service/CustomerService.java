package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;

import java.util.List;

public interface CustomerService{
    //GET
    List<Customer> findCustomerAll();
    List<Customer> findCustomersByRegion(Region region);

    //POST
    Customer createCustomer(Customer customer);
    //PUT
    Customer updateCustomer(Customer customer);
    //DELETE
    /*Anterior version se borra con el id, aqui
    * se borrará pasandole el objeto*/
    Customer deleteCustomer(Customer customer);

    //GET
    Customer getCustomer(Long id);
}
