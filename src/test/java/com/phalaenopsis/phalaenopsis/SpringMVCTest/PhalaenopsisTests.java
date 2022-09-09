package com.phalaenopsis.phalaenopsis.SpringMVCTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PhalaenopsisTests {
    MockMvc mockMvc;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    }

    @Test
    public void testSearchTopics() throws Exception {
        MockHttpServletRequestBuilder topicRequest = MockMvcRequestBuilders.post("/search?searchString=keyword");

        this.mockMvc.perform(topicRequest).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("topics"))
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("search"));
    }

    @Test
    public void loginUser() throws Exception {


        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username", "user0").param("password", "pass0");
        this.mockMvc.perform(loginRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username", "user1").param("password", "pass1");
        this.mockMvc.perform(loginRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?error=true"));
    }

    @Test
    public void registerUser() throws Exception {
        //String username, String password, String repeatPassword, String name, String surname
        MockHttpServletRequestBuilder registerRequest = MockMvcRequestBuilders.post("/register")
                .param("username", "someusername")
                .param("password", "somepassword")
                .param("repeatPassword", "somepassword")
                .param("name", "NameOfUser")
                .param("surname", "SurnameOfUser");

        this.mockMvc.perform(registerRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("login"));



        //test login with newly registered user
        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username", "someusername").param("password", "somepassword");
        this.mockMvc.perform(loginRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void registerUserFailTest() throws Exception {
        //different repeatPassword
        MockHttpServletRequestBuilder registerRequest = MockMvcRequestBuilders.post("/register")
                .param("username", "someusername")
                .param("password", "somepassword")
                .param("repeatPassword", "someOTHERpassword")
                .param("name", "NameOfUser")
                .param("surname", "SurnameOfUser");

        this.mockMvc.perform(registerRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register?error=true"));


        //same username as user that already exists
        registerRequest = MockMvcRequestBuilders.post("/register")
                .param("username", "user0")
                .param("password", "somepassword")
                .param("repeatPassword", "somepassword")
                .param("name", "NameOfUser")
                .param("surname", "SurnameOfUser");

        this.mockMvc.perform(registerRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register?error=true"));



        //empty username
        registerRequest = MockMvcRequestBuilders.post("/register")
                .param("username", "")
                .param("password", "somepassword")
                .param("repeatPassword", "somepassword")
                .param("name", "NameOfUser")
                .param("surname", "SurnameOfUser");

        this.mockMvc.perform(registerRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register?error=true"));
    }





    @Test
    @WithMockUser(username = "user0")
    public void updateUser() throws Exception {

        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username", "user0").param("password", "pass0");
        this.mockMvc.perform(loginRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));



        MockHttpServletRequestBuilder updateRequest  = MockMvcRequestBuilders.post("/updateprofile")
                .param("password", "Password123")
                .param("repeatPassword", "Password123")
                .param("name", "TestingNewName123")
                .param("surname", "TestingNewSurname123");

        this.mockMvc.perform(updateRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile?success=true"));

    }


    @Test
    @WithMockUser(username = "user0")
    public void getCertificatePage() throws Exception {
        //login user
        MockHttpServletRequestBuilder loginRequest = MockMvcRequestBuilders.post("/login")
                .param("username", "user0").param("password", "pass0");
        this.mockMvc.perform(loginRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));


        //post certification wrong answers
        MockHttpServletRequestBuilder certificationRequest = MockMvcRequestBuilders.post("/certification")
                .param("question1", "3")
                .param("question2", "3")
                .param("question3", "3")
                .param("question4", "3")
                .param("question5", "3")
                .param("question6", "3");

        this.mockMvc.perform(certificationRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/certification?error=true"));



        //post certification answers
        certificationRequest = MockMvcRequestBuilders.post("/certification")
                .param("question1", "2")
                .param("question2", "1")
                .param("question3", "2")
                .param("question4", "1")
                .param("question5", "1")
                .param("question6", "2");

        this.mockMvc.perform(certificationRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        MockHttpServletRequestBuilder certificate = MockMvcRequestBuilders.get("/certificate");
        this.mockMvc.perform(certificate)
                .andExpect(MockMvcResultMatchers.model().attribute("name", "Name"))
                .andExpect(MockMvcResultMatchers.model().attribute("surname", "Surname"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }


    @Test
    public void getLoginPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/login");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("login"));
    }


    @Test
    public void getRegisterPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/register");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.view().name("register"));
    }





    @Test
    public void getHomePage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a1", true))
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void getRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots"));
    }



    @Test
    public void getAirRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_air");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_air"));
    }

    @Test
    public void getBlackRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_black");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_black"));
    }

    @Test
    public void getBrownRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_brown");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_brown"));
    }

    @Test
    public void getGreenRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_green");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_green"));
    }

    @Test
    public void getWhiteRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_white");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_white"));
    }

    @Test
    public void getYellowRootsPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/roots_yellow");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a2", true))
                .andExpect(MockMvcResultMatchers.view().name("roots_yellow"));
    }



    @Test
    public void getLeavesPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/leaves");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a3", true))
                .andExpect(MockMvcResultMatchers.view().name("leaves"));
    }

    @Test
    public void getStemPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/stem");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a4", true))
                .andExpect(MockMvcResultMatchers.view().name("stem"));
    }

    @Test
    public void getFlowersPage() throws Exception {
        MockHttpServletRequestBuilder homePageRequest = MockMvcRequestBuilders.get("/flowers");
        this.mockMvc.perform(homePageRequest).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.model().attribute("a5", true))
                .andExpect(MockMvcResultMatchers.view().name("flowers"));
    }


}
