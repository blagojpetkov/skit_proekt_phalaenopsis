package com.phalaenopsis.phalaenopsis.service;


import com.phalaenopsis.phalaenopsis.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    User findById(Long id);


    List<User> listAll();

    User findByUsername(String username);

    User save(User user);

    User getAuthenticatedUser();

    User create(String username, String password, String repeatPassword, String role, String name, String surname);

    User updateProfile(/*String username, */String password, String repeatPassword, String role, String name, String surname);
}
