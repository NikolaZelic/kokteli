package com.zelic.demo.services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.zelic.demo.entities.UserEntity;
import com.zelic.demo.repositories.UsersRepository;
import com.zelic.demo.utilities.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UsersService {

    @Resource
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public FilterProvider publicUsersFilter(){
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("usersFilter", SimpleBeanPropertyFilter.serializeAllExcept("cocktails", "password"));
        return filterProvider;
    }

    public FilterProvider publicUsersFilterWithCocktails(){
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("usersFilter", SimpleBeanPropertyFilter.serializeAllExcept("password"));
        return filterProvider;
    }

    public Iterable<UserEntity> getAllUsers(){
        return usersRepository.findAll();
    }

    public Integer createUser(UserEntity user){
        UserEntity copy = new UserEntity(user);
        copy.setId(null);
        setEncriptedUserPassword(copy, user.getPassword());

        UserEntity updatedUser = usersRepository.save(copy);
        return updatedUser.getId();
    }

    public void saveUser(UserEntity user){
        usersRepository.save(user);
    }

    public Optional<UserEntity> getUser(String username){
        return usersRepository.findUserEntityByUsername(username);
    }

    public Optional<UserEntity> getUser(Integer id){
        return usersRepository.findById(id);
    }

    public boolean userAuthorized(UserEntity user){
        Optional<UserEntity> optionalDbUser = getUser(user.getUsername());

        if( !optionalDbUser.isPresent() )
            return false;

        UserEntity dbUser = optionalDbUser.get();
        return passwordEncoder.matches(user.getPassword(), dbUser.getPassword());
    }

//  ------ PRIVATE METHODS -----
    private void setEncriptedUserPassword(UserEntity user, String password){
        user.setPassword( passwordEncoder.encode(password) );
    }


}
