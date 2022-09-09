package com.phalaenopsis.phalaenopsis.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "MyUsers")
public class User {

    public User() {
    }

    public User(String username, String password, String role, String name, String surname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.surname = surname;

        this.email = username+"@"+"gmail.com";
    }
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;

    private String password;

    private String role;
    private String name;
    private String surname;

    public String getFullName(){
        return name + ' ' + surname;
    }
    public String getRoles(){
        return role;
    }

    public String isEnabled(){
        return "true";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToOne
    public Certificate certificate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
