package com.phalaenopsis.phalaenopsis.MutationTesting;

import com.phalaenopsis.phalaenopsis.domain.Topic;
import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.UserRepository;
import com.phalaenopsis.phalaenopsis.service.UserService;
import com.phalaenopsis.phalaenopsis.service.impl.TopicServiceImpl;
import com.phalaenopsis.phalaenopsis.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplMutationTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private User authenticatedUser;
    private User ordinaryUser;
    private List<User> users;
    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);

        authenticatedUser = new User("username", "password", "role", "name", "surname");
        ordinaryUser = new User("ordinaryUsername", "password", "role", "name", "surname");
        users = new ArrayList<>();
        users.add(authenticatedUser);
        users.add(ordinaryUser);

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(authenticatedUser));
//        when(userRepository.findByUsername("")).thenReturn(null);
        when(userRepository.findById(123L)).thenReturn(Optional.of(authenticatedUser));
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.save(any())).then(returnsFirstArg());
        when(passwordEncoder.encode(anyString())).thenReturn("encodedpassword");
        userService = Mockito.spy(new UserServiceImpl(passwordEncoder, userRepository));


        Authentication authentication = Mockito.mock(Authentication.class);
        // Mockito.whens() for your authorization object
        when(authentication.getName()).thenReturn("username");
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);


    }

    @Test
    public void getAuthenticatedUserTest(){
        Assert.assertEquals(authenticatedUser, userService.getAuthenticatedUser());
    }

    @Test
    public void findByIdTest(){
        Assert.assertEquals(authenticatedUser, userService.findById(123L));
//        Assert.assertThrows(RuntimeException.class, () -> {
//            userService.findById(456L);
//        });
    }

    @Test
    public void listAllTest(){
        Assert.assertEquals(userService.listAll(), users);
    }

    @Test
    public void findByUsernameTest(){
        Assert.assertEquals(userService.findByUsername("username"), authenticatedUser);
    }

    @Test
    public void saveTest(){
        Assert.assertEquals(userService.save(authenticatedUser), authenticatedUser);
    }

    @Test
    public void createTest(){
        Assert.assertThrows(RuntimeException.class,
                () -> {
                userService
                .create("username",
                        "password",
                        "password",
                        "role",
                        "name",
                        "surname");}

        );
        User user = new User("createuser",
                            "encodedpassword",
                            "role",
                            "name",
                            "surname");
        Assert.assertEquals(userService
                .create("createuser",
                        "password",
                        "password",
                        "role",
                        "name",
                        "surname"), user);
    }

    @Test
    public void updateProfileTest(){
        User updatedUser = new User("username", "encodedpassword", "role", "updatedname", "updatedsurname");
        Assert.assertEquals(updatedUser, userService.updateProfile("password",
                "password",
                "role",
                "updatedname",
                "updatedsurname"));
        Assert.assertThrows(RuntimeException.class, ()->{
            userService.updateProfile("password", "", "role", "name", "surname");
        });
    }

    @Test
    public void loadUserByUsernameTest(){
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                "username", "password",
                Collections.singletonList(new SimpleGrantedAuthority("role"))
        );
        Assert.assertEquals(userDetails, userService.loadUserByUsername("username"));
    }
}
