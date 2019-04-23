package com.zelic.demo.services;

import com.zelic.demo.entities.CocktailEntity;
import com.zelic.demo.repositories.CocktailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CocktailsService {

    @Autowired
    private CocktailsRepository cocktailsRepository;

    public Iterable<CocktailEntity> getAllCocktails(){
        return cocktailsRepository.findAll();
    }

    public Integer createCocktail(CocktailEntity cocktail) {
        Objects.requireNonNull(cocktail);
        cocktail.setId(null);
        CocktailEntity createdCocktail = cocktailsRepository.save(cocktail);
        return createdCocktail.getId();
    }

    public Integer createCocktail(CocktailEntity cocktail, List<Integer> ingredients){
        final Integer cocID = createCocktail(cocktail);
        if(Objects.isNull(ingredients))
            return cocID;
//        ingredients.forEach( ingID ->{
//            CocIngEntity r = new CocIngEntity(cocID, ingID);
//            cocIngRepository.save(r);
//
//        });
        return cocID;
    }

    public Optional<CocktailEntity> getCocktail(Integer id) {
        return cocktailsRepository.findById(id);
    }
}
