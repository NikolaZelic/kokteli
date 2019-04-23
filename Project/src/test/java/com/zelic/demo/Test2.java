package com.zelic.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.zelic.demo.entities.CocktailEntity;
import com.zelic.demo.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test2 {

    public static void main(String[] args) throws JsonProcessingException {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Optional<Integer> any = list.stream().filter(el -> el == 5).findAny();
        System.out.println(any.isPresent());
    }
}
