package com.zelic.demo.repositories;

import com.zelic.demo.entities.CocktailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailsRepository extends CrudRepository<CocktailEntity, Integer> {

}
