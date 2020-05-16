package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.apache.coyote.Response;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //LISTAR PRODUCTOS

    //Listar todos los productos y listar por categoria

    /*@PathVariable=Datos se envia en el url
      @RequestParam=Datos se envia en el cuerpo de la peticion
    */
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId",required=false)Long categoryId)
    {
        List<Product> products=new ArrayList<>();

        if(null==categoryId)
        {
            products=productService.listAllProduct();
            if(products.isEmpty())
            {
                return ResponseEntity.noContent().build();
            }
        }else
        {
            products=productService.findByCategory(Category.builder().id(categoryId).build());
            if(products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id)
    {
        Product product=productService.getProduct(id);
        if(null==product)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }


    //REVISAR ESTA FUNCION
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        //Si no se esta cumpliendo las validacions
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Product productCreate=productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product)
    {
        product.setId(id);
        Product productDB=productService.updateProduct(product);
        if(productDB==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id)
    {
        Product productDelete=productService.deleteProduct(id);
        if(productDelete==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDelete);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id")Long id,
                                                      @RequestParam(name="quantity",required=true)Double quantity)
    {
        Product product=productService.updateStock(id,quantity);
        if(product==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
}
