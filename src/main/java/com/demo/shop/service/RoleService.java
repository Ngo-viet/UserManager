package com.demo.shop.service;

import com.demo.shop.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoleService {
    List<Role> findAll();

}
