package com.example.demo.service.Impl;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    //pasarle la inyeccion de dependencias
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        /*
        * 1. Validar si ya existe un cliente con el mismo id*/
        Customer customerDB= customerRepository.findByNumberId(customer.getNumberId());
        if(customerDB!=null)
        {
            return customerDB;
        }
        customer.setState("CREATED");
        customerDB=customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customerDB=this.getCustomer(customer.getId());
        if(customerDB==null)
        {
            return null;
        }
        customerDB.setFirstName(customer.getFirstName());
        customerDB.setLastName(customer.getLastName());
        customerDB.setEmail(customer.getEmail());
        customerDB.setPhotoUrl(customer.getPhotoUrl());

        return customerRepository.save(customerDB);
    }

    @Override
    public Customer deleteCustomer(Customer customer) {
        Customer customerDB=this.getCustomer(customer.getId());
        if(customerDB==null)
        {
            return null;
        }
        customerDB.setState("DELETED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
