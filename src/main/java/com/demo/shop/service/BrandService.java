package com.demo.shop.service;

import com.demo.shop.entities.Brand;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface BrandService {

    Brand findById(Long id);

    List<Brand> findAll();
}
