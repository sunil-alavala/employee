package com.employee.service;

import com.employee.entity.User;
import com.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String name) {
        return userRepository.findByUsername(name);
    }
}
