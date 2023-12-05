package com.demo.shop.service.impl;

import com.demo.shop.entities.Brand;
import com.demo.shop.entities.Category;
import com.demo.shop.repository.BrandRepository;
import com.demo.shop.repository.CategoryRepository;
import com.demo.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
