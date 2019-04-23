package com.zelic.demo.controlers;

import com.zelic.demo.entities.CocktailEntity;
import com.zelic.demo.entities.UserEntity;
import com.zelic.demo.services.UsersService;
import com.zelic.demo.utilities.messages.MessagesConfiguration;
import com.zelic.demo.utilities.messages.StandardMessage;
import com.zelic.demo.utilities.messages.StandardMessageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;
    @Autowired
    private MessagesConfiguration messagesConfiguration;

    @GetMapping
    public MappingJacksonValue getAllUsers(){
        Iterable<UserEntity> allUsers = usersService.getAllUsers();
        MappingJacksonValue rez = new MappingJacksonValue(allUsers);
        rez.setFilters(usersService.publicUsersFilter());
        return rez;
    }

    @PostMapping
    public Map<String, Integer> createUser(@RequestBody @Valid UserEntity user){
        Map<String, Integer> rez = new HashMap<>();
        Integer id = usersService.createUser(user);
        rez.put("id", id);
        return rez;
    }

    @RequestMapping(path = "/{id}")
    public MappingJacksonValue getUser(@PathVariable Integer id, @RequestParam(name = "cocktails", required = false, defaultValue = "false") boolean cocktails) throws Exception {
        Optional<UserEntity> userEntity = usersService.getUser(id);
        MappingJacksonValue rez = new MappingJacksonValue(userEntity);
        if(cocktails)
            rez.setFilters(usersService.publicUsersFilterWithCocktails());
        else
            rez.setFilters(usersService.publicUsersFilter());
        return rez;
    }

    @RequestMapping("/{id}/cocktails")
    public StandardMessage getUserCocktails(@PathVariable Integer id){
        Optional<UserEntity> optionalUserEntity = usersService.getUser(id);
        if(!optionalUserEntity.isPresent()){
            return messagesConfiguration.standardMessage(StandardMessageStatus.INVALID_REQUEST, "There is no user with ID: " + id);
        }
        UserEntity user = optionalUserEntity.get();
        StandardMessage msg = messagesConfiguration.standardMessage(StandardMessageStatus.OK);
        msg.addData("cocktails", user.getCocktails());
        return msg;
    }

    @RequestMapping(path = "/{id}/cocktails", method = RequestMethod.PUT)
    public StandardMessage editUserCocktails(@PathVariable Integer id, @RequestBody List<CocktailData> cocktails) {
        Optional<UserEntity> optionalUserEntity = usersService.getUser(id);
        if(!optionalUserEntity.isPresent()){
            return messagesConfiguration.standardMessage(StandardMessageStatus.INVALID_REQUEST, "There is no user with ID: " + id);
        }
        UserEntity userEntity = optionalUserEntity.get();
        List<CocktailEntity> currentCocktails = userEntity.getCocktails();

        cocktails.forEach( cocData -> {
            CocktailEntity coc = new CocktailEntity(cocData.getId());
            if(cocData.getMethod()==EditCocktailMethod.DELETE){
                currentCocktails.removeIf(el -> el.getId()==coc.getId());
            }
            else if(!currentCocktails.stream().filter(el -> el.getId()==coc.getId()).findAny().isPresent()){
                    currentCocktails.add(coc);
            }
        } );

        usersService.saveUser(userEntity);
        return messagesConfiguration.standardMessage(StandardMessageStatus.OK);
    }
}

class CocktailData {
    private int id;
    private EditCocktailMethod method;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EditCocktailMethod getMethod() {
        return method;
    }

    public void setMethod(EditCocktailMethod method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "CocktailData{" +
                "id=" + id +
                ", method=" + method +
                '}';
    }
}

enum EditCocktailMethod {
    ADD,
    DELETE
}
