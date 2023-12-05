package com.demo.shop.service.impl;

import com.demo.shop.entities.Size;
import com.demo.shop.repository.SizeRepository;
import com.demo.shop.service.SizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findAllById(Long id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Size not found"));
    }
}
