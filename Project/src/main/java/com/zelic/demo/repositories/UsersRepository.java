package com.zelic.demo.repositories;

import com.zelic.demo.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserEntityByUsername(String username);

}
