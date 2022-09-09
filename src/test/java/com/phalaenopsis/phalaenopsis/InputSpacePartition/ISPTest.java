package com.phalaenopsis.phalaenopsis.InputSpacePartition;


import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.UserRepository;
import com.phalaenopsis.phalaenopsis.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class ISPTest {


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void init(){
        userRepository.deleteAll();
    }


    //@Override
    //    public User create(String username, String password, String repeatPassword, String role, String name, String surname) {
    //        if(!Objects.equals(username, "") && password.equals(repeatPassword) && findByUsername(username) == null) {
    //            User user = new User(username, passwordEncoder.encode(password), role, name, surname);
    //            return userRepository.save(user);
    //        }
    //        else throw new RuntimeException();
    //    }


    //Characteristics:


    //username is null
    //username is empty string

    //password is null
    //password is empty string

    //repeatPassword is null
    //repeatPassword is empty string

    //role is null
    //role is empty string

    //name is null
    //name is empty string

    //surname is null
    //surname is empty string

    //each characteristic is partitioned into True and False blocks



    @Test
    public void FFFFFFFFFFFF_Test(){
        Assert.assertNotNull(userService.create("username",
                                                "password",
                                                "password",
                                                "Administrator",
                                                "Name",
                                                "Surname"));
    }

    @Test
    public void TFFFFFFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create(null,
                "password",
                "password",
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FTFFFFFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create("",
                "password",
                "password",
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FFTFFFFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create("username",
                null,
                "password",
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FFFTFFFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create("username",
                "",
                "password",
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FFFFTFFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create("username",
                "password",
                null,
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FFFFFTFFFFFF_Test(){
        Assert.assertThrows(RuntimeException.class, ()->{userService.create("username",
                "password",
                "",
                "Administrator",
                "Name",
                "Surname");});
    }

    @Test
    public void FFFFFFTFFFFF_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                null,
                "Name",
                "Surname"));
    }

    @Test
    public void FFFFFFFTFFFF_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                "",
                "Name",
                "Surname"));
    }

    @Test
    public void FFFFFFFFTFFF_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                "Administrator",
                null,
                "Surname"));
    }

    @Test
    public void FFFFFFFFFTFF_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                "Administrator",
                "",
                "Surname"));
    }

    @Test
    public void FFFFFFFFFFTF_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                "Administrator",
                "Name",
                null));
    }

    @Test
    public void FFFFFFFFFFFT_Test(){
        Assert.assertNotNull(userService.create("username",
                "password",
                "password",
                "Administrator",
                "Name",
                ""));
    }




}
