package com.zelic.demo.controlers;

import com.zelic.demo.entities.IngredientEntity;
import com.zelic.demo.services.IngredientsService;
import com.zelic.demo.utilities.messages.MessagesConfiguration;
import com.zelic.demo.utilities.messages.StandardMessage;
import com.zelic.demo.utilities.messages.StandardMessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@RequestMapping("/ingredients")
public class IngredientsController {

    @Resource
    private IngredientsService ingredientsService;
    @Autowired
    private MessagesConfiguration messagesConfiguration;

    @GetMapping
    public Iterable<IngredientEntity> getAllIngredients(){
        return ingredientsService.getAllIngredients();
    }

    @PostMapping
    public StandardMessage createIngredient(@RequestBody @Valid IngredientEntity ingredientEntity){
        Integer id = ingredientsService.createIngredient(ingredientEntity);
        StandardMessage message = messagesConfiguration.standardMessage(StandardMessageStatus.OK);
        message.addData("id", id);
        return message;
    }
}
