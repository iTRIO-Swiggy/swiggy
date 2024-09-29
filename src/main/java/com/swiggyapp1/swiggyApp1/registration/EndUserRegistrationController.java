package com.swiggyapp1.swiggyApp1.registration;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class EndUserRegistrationController{

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        System.out.println("in user registration ");
    }
}
