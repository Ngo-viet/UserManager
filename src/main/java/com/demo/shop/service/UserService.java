package com.demo.shop.service;

import com.demo.shop.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void save(User user);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);
    User findById(Long id);

    List<User> findAll();
}
