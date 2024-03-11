package com.info902.projet.controller;

import com.info902.projet.controller.request.RegisterRequest;
import com.info902.projet.model.User;
import com.info902.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> Register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.createUser(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<User> Login(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.loginUser(registerRequest));
    }

    @GetMapping("/hello")
    public String Hello(){
        return "Hello";
    }
}
