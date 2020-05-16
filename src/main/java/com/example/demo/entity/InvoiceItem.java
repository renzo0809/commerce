package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name="invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive(message="La cantidad debe ser mayor que cero")
    private Double quantity;
    private Double price;

    @Column(name="product_id")
    private Long productId;

    //Transient es para que no aparezca en la base de datos
    @Transient
    private Double subTotal;



    public Double getSubTotal(){
        if(this.price>0&&this.quantity>0)
        {
            return this.quantity*this.price;
        }else {
            return (double)0;
        }
    }
    public InvoiceItem(){
        this.quantity=(double)0;
        this.price=(double)0;

    }

}
