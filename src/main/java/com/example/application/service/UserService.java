package com.example.application.service;
public interface UserService {
    void registerUser(String email, String password, String rePassword);

    boolean auth(String email, String password);

    void confirmUser(String parameter);
}
