package com.zelic.demo.repositories;

import com.zelic.demo.entities.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends CrudRepository<IngredientEntity, Integer> {

}
