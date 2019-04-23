package com.zelic.demo.controlers;

import com.zelic.demo.entities.CocktailEntity;
import com.zelic.demo.services.CocktailsService;
import com.zelic.demo.utilities.messages.MessagesConfiguration;
import com.zelic.demo.utilities.messages.StandardMessage;
import com.zelic.demo.utilities.messages.StandardMessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cocktails")
public class CocktailsController {

    @Resource
    private CocktailsService cocktailsService;
    @Autowired
    private MessagesConfiguration messagesConfiguration;

    @GetMapping
    public Iterable<CocktailEntity> getAllCocktails(){
        return cocktailsService.getAllCocktails();
    }

    @PostMapping
    public StandardMessage createCocktail(@RequestBody @Valid NewCocktail cocktail) {
        Integer id = cocktailsService.createCocktail(cocktail.getCocktail(), cocktail.getIngredients());
        StandardMessage message = messagesConfiguration.standardMessage(StandardMessageStatus.OK);
        message.addData("id", id);
        return message;
    }

    @RequestMapping("/{id}")
    public Optional<CocktailEntity> getCocktail(@PathVariable Integer id){
        return cocktailsService.getCocktail(id);
    }
}

class NewCocktail {
    @NotNull @Valid
    private CocktailEntity cocktail;

    private List<Integer> ingredients;

    public NewCocktail() {

    }

    public CocktailEntity getCocktail() {
        return cocktail;
    }

    public void setCocktail(CocktailEntity cocktail) {
        this.cocktail = cocktail;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "NewCocktail: {\n" +
                "\tcocktail=" + cocktail +
                ", \n\tingredients=" + ingredients +
                '}';
    }
}
