package com.example.demo.api;

import com.example.demo.entities.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public Iterable<UsersEntity> getUsers(){
        return usersRepository.findAll();
    }

}
