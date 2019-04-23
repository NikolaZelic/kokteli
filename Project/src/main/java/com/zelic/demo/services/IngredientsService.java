package com.zelic.demo.services;

import com.zelic.demo.entities.IngredientEntity;
import com.zelic.demo.repositories.IngredientsRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IngredientsService {

    @Resource
    private IngredientsRepository ingredientsRepository;

    public Integer createIngredient(IngredientEntity ingredient){
        IngredientEntity savedIngredient = ingredientsRepository.save(ingredient);
        return savedIngredient.getId();
    }

    public Iterable<IngredientEntity> getAllIngredients() {
        return ingredientsRepository.findAll();
    }
}
