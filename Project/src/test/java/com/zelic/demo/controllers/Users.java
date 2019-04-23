package com.zelic.demo.controllers;

import com.zelic.demo.controlers.UsersController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class Users {

    @Autowired
    private UsersController usersController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        this.mockMvc.
                perform(get("/users")).
                andDo(print()).
                andExpect( status().isOk() );
    }

    @Test
    public void createUser() throws Exception {
        String json = "{\"username\": \"Pera zdera\", \"password\": \"123\"}";


        mockMvc.perform( post("/users").contentType(MediaType.APPLICATION_JSON).content(json) )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
