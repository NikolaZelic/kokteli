package com.zelic.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    @Autowired
    EntityManager entityManager;

    @Test
    public void test(){
        Query s = entityManager.createQuery("select u from UserEntity u");
        s.getResultList().forEach(System.out::println);


    }
}
