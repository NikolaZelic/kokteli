package com.zelic.demo.controlers;

import com.zelic.demo.entities.UserEntity;
import com.zelic.demo.services.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UsersService usersService;

    @PostMapping
    public Map<String, Object> loginUser(@RequestBody @Valid UserEntity user, HttpServletRequest request){
        final Map<String, Object> rez = new HashMap<>();
        if(usersService.userAuthorized(user)){
            rez.put("status", "ok");
            // Start session
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
        }
        else {
            rez.put("status", "err");
            rez.put("message", "Invalid user's credentials");
        }
        return rez;
    }
}


