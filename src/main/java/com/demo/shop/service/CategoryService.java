package com.demo.shop.service;

import com.demo.shop.entities.Brand;
import com.demo.shop.entities.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category findById(Long id);

    List<Category> findAll();
}
