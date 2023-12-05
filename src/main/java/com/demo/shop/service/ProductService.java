package com.demo.shop.service;

import com.demo.shop.entities.Product;
import com.demo.shop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void save(Product product);
    Product create(Product product);
    Product update(Long id,Product product);
    void delete(Long id);
    Product findById(Long id);

    List<Product> findAll();
}
