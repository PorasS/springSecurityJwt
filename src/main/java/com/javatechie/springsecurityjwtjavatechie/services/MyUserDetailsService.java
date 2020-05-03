package com.javatechie.springsecurityjwtjavatechie.services;

import com.javatechie.springsecurityjwtjavatechie.entities.User;
import com.javatechie.springsecurityjwtjavatechie.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findByUserName(username);

     return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), new   ArrayList<>());
    }
}
