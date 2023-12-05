package com.demo.shop.service.impl;

import com.demo.shop.entities.Brand;

import com.demo.shop.repository.BrandRepository;

import com.demo.shop.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}
