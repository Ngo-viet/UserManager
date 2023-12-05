package com.demo.shop.service.impl;

import com.demo.shop.entities.User;
import com.demo.shop.repository.UserRepository;
import com.demo.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        if (user.getUser_email() == null  || user.getUser_email().isEmpty()) {
            return null;
        }
        if (user.getUsername() == null  || user.getUsername().isEmpty()) {
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public void save(User user){
        //user.setPassword(passwordEncoder.encode(user.getPassword()));

        this.userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User fromDB = userRepository.findById(id).orElse(null);
        if (fromDB == null) {
            return null;
        }
        fromDB.setUser_email(user.getUser_email());
        fromDB.setUsername(user.getUsername());
        return userRepository.save(fromDB);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Người dùng không hợp lệ:" + id));

        // Xóa liên kết với các vai trò trước
        user.getRoles().clear();
        userRepository.save(user);

        // Sau đó xóa người dùng
        userRepository.deleteById(id);
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }





}
