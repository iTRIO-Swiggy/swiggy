package com.swiggyapp1.swiggyApp1.registration.controller;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Add a new user", description = "Creates a new user and returns the created user object.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user input")
    })
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all users", description = "Returns a list of all users.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved users")
    @GetMapping("/all")
    public ResponseEntity<List<User>> showUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @Operation(summary = "Get users by type", description = "Fetches users based on their type.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users by type"),
            @ApiResponse(responseCode = "404", description = "User type not found")
    })
    @GetMapping("/usersByType/{userType}")
    public ResponseEntity<List<User>> showUsersByType(@PathVariable String userType) {
        List<User> allUsers = userService.getAllUsersByType(userType);
        return ResponseEntity.ok(allUsers);
    }

    @Operation(summary = "Update user details", description = "Updates the details of an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserDetails(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user by their ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable BigInteger id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
