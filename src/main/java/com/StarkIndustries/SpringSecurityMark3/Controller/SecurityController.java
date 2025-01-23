package com.StarkIndustries.SpringSecurityMark3.Controller;

import com.StarkIndustries.SpringSecurityMark3.Models.Users;
import com.StarkIndustries.SpringSecurityMark3.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {

    @Autowired
    public UsersService usersService;

    @GetMapping("/greetings")
    public String greetings(){
        return "Greetings\n I am Optimus Prime";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Users users){
        if(usersService.signUp(users))
            return ResponseEntity.status(HttpStatus.OK).body("User Signup Successfully!!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exist!!");
    }

    @GetMapping("/login/{username}")
    public ResponseEntity<String> login(@PathVariable("username") String username){
        if(usersService.login(username))
            return ResponseEntity.status(HttpStatus.OK).body("Login Successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to Login!!");
    }
}
