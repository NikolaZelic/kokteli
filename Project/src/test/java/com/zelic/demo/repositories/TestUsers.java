package com.zelic.demo.repositories;

import com.zelic.demo.entities.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUsers {

//    @Autowired
//    private TestEntityManager testEntityManager;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void createSelectDelete(){
        String username = "Some new username";
        String password = "123";
        UserEntity user = new UserEntity(null, username, password);

        // CREATE
        usersRepository.save(user);

        // SELECT
        Optional<UserEntity> finedUser = usersRepository.findUserEntityByUsername(username);
        System.out.println(finedUser.get());

        // DELETE
        usersRepository.delete(finedUser.get());
    }
}
