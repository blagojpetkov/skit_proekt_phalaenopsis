package com.phalaenopsis.phalaenopsis.service.impl;

import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.UserRepository;
import com.phalaenopsis.phalaenopsis.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User getAuthenticatedUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(String username, String password, String repeatPassword, String role, String name, String surname) {
        if(username!=null && !Objects.equals(username, "") && password.equals(repeatPassword) && findByUsername(username) == null) {
            User user = new User(username, passwordEncoder.encode(password), role, name, surname);
            return userRepository.save(user);
        }
        else throw new RuntimeException();
    }


    @Override
    public User updateProfile(/*String username, */String password, String repeatPassword, String role, String name, String surname) {
        User user = this.getAuthenticatedUser();

        //!Objects.equals(username, "") &&
        //&& (user.getUsername().equals(username) || findByUsername(username) == null)
        //user.setUsername(username);

        if(user!=null && password.equals(repeatPassword))
        {
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(passwordEncoder.encode(password));
            return userRepository.save(user);
        }

        throw new RuntimeException();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

        return userDetails;
    }
}
