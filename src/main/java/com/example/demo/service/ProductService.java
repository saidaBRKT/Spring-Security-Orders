package com.example.demo.service;


import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;


    public Product save(Product product){
        product.setUuid(UUID.randomUUID());
        product=productRepository.save(product);
        return product;
    }
    public Product update(Product product){
        Product p = productRepository.findByUuid(product.getUuid());
        p.setId(product.getId());
        p.setPrice(product.getPrice());
        p.setUuid(product.getUuid());
        p.setName(product.getName());
        product=productRepository.save(product);
        return product;
    }
    public int delete(UUID uuid){
        return productRepository.deleteByUuid(uuid);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
