package com.example.demo.repository;


import com.example.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    //retornar informacion de todas las facturas
    List<Invoice> findByCustomerId(Long customerId);

    /*Si no uso los keywords (findBy...)
    *
    *
    * @Query("SELECT i FROM Invoice i WHERE i.customerId=?1")
    *   List<Invoice> getInvoiceByCustomerId(Long customerId);
    *
    *
    * */

    Invoice findByNumberInvoice(String numberInvoice);
}
