package com.zelic.demo.services;

import com.zelic.demo.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Users {

    @Autowired
    private UsersService usersService;

    @Test
    public void createSelect(){
        String username = "miroslav";
        String password = "123";
        UserEntity user = new UserEntity(null, username, password);

        // CREATE
        Integer id = usersService.createUser(user);

        // SELECT BY ID
        System.out.println(usersService.getUser(id).get());

        // SELECT BY USERNAME
        System.out.println(usersService.getUser(username).get());

    }
}
