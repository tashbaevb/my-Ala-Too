package com.example.application.service.impl;

import com.example.application.entity.User;
import com.example.application.exception.BadCredentialsException;
import com.example.application.exception.BadRequestException;
import com.example.application.repo.RoleRepository;
import com.example.application.repo.UserRepository;
import com.example.application.service.MailSenderService;
import com.example.application.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.application.entity.Role;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final MailSenderService mailSenderService;


    @Override
    public void registerUser(String email, String password, String rePassword) {
        String confirmCode = UUID.randomUUID().toString();
        if (userRepository.findByEmail(email).isPresent())
            throw new BadCredentialsException("this user is already exist!");
        if (!password.equals(rePassword))
            throw new BadCredentialsException("the password should match!");
        User user = new User();
        user.setEmail(email);
        user.setRegDate(LocalDateTime.now());
        user.setIsActive(false);
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByCode("ADMIN"));
        user.setConfirmCode(confirmCode);
        user.setRoles(roles);



        userRepository.save(user);

        mailSenderService.ss(user.getEmail(), "confirm ur email","\n\nhttp://localhost:8977/confirm/"+confirmCode);


    }


    @Override
    public boolean auth(String email, String password) {
        System.out.println("run!\n\n");
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty())
            throw new BadCredentialsException("user not found with this email!: "+email);
        try {
            user.get().getRoles().size();

            if (!user.get().getIsActive()){
                throw new BadRequestException("Please, confirm your email");
            }
            System.out.println("Attempting authentication...");

//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    email,
//                    password));
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    email,
                    password));

            // Set the authentication result into the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("Successfully authenticated.");
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed!");
            e.printStackTrace();
            throw new BadCredentialsException("Bad credentials");
        }

        System.out.println("return true!\n\n");

        return true;

    }

    @Override
    public void confirmUser(String parameter) {
        Optional<User> user = userRepository.findByConfirmCode(parameter);
        if (user.isPresent()){
            user.get().setIsActive(true);
            userRepository.save(user.get());
        }
    }
}
