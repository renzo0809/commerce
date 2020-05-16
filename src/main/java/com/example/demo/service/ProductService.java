package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> listAllProduct();

    Product getProduct(Long id);

    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Long id);

    List<Product> findByCategory(Category category);
    Product updateStock(Long id, Double quantity);


}
