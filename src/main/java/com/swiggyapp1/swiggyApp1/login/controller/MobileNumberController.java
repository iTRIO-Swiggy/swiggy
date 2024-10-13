package com.swiggyapp1.swiggyApp1.login.controller;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/users/mobile")
public class MobileNumberController {

  @Autowired private UserRepository userRepository;

  @PostMapping("/request")
  public ResponseEntity<String> handleMobileRequest(
      @RequestParam BigInteger userId,
      @RequestParam String email,
      @RequestParam String mobilenumber) {

    // Find the user by ID
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return ResponseEntity.badRequest().body("User not found");
    }

    // Check if the mobile number is null
    if (user.getMobilenumber() == null) {
      user.setMobilenumber(mobilenumber);
      userRepository.save(user);
      return ResponseEntity.ok("Mobile number updated successfully");
    } else {
      return ResponseEntity.ok("Mobile number already exists: " + user.getMobilenumber());
    }
  }
}
