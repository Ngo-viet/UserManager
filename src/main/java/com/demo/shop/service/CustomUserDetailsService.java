package com.demo.shop.service;

import com.demo.shop.entities.User;
import com.demo.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        CustomUserDetails customUserDetails = null;
        if(user!= null){
            customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);
        }else{
            throw new UsernameNotFoundException("user not exist with name: " + username);
        }
        return customUserDetails;
    }
}
