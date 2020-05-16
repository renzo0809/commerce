package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByNumberId(String numberId);
    /*Se devuelve como lista porque lo mas probable es
    * que se muestre en un table*/
    List<Customer> findByLastName(String lastName);

    List<Customer> findByRegion(Region region);
}
