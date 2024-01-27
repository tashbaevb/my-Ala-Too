package com.example.application.service;

import com.example.application.entity.User;
import com.example.application.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean auth(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.map(user -> passwordEncoder.matches(password, user.getPassword())).orElse(false);
    }
}
