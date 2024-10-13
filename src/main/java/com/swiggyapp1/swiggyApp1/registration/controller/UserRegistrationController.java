package com.swiggyapp1.swiggyApp1.registration.controller;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {

  @Autowired UserService userService;

  @PostMapping("/add")
  public ResponseEntity<User> addUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> showUsers() {
    List<User> allUsers = userService.getAllUsers();
    return ResponseEntity.ok(allUsers);
  }

  @GetMapping("/usersByType/{userType}")
  public ResponseEntity<List<User>> showUsersByType(@PathVariable String userType) {
    List<User> allUsers = userService.getAllUsersByType(userType);
    return ResponseEntity.ok(allUsers);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<User> updateUserDetails(@RequestBody User user) {
    User updatedUser = userService.updateUser(user);
    return ResponseEntity.ok(updatedUser);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable BigInteger id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }
}
