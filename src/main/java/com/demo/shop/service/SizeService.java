package com.demo.shop.service;

import com.demo.shop.entities.Role;
import com.demo.shop.entities.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {
    List<Size> findAll();

    Size findAllById(Long id);
}
