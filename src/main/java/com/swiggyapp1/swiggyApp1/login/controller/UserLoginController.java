package com.swiggyapp1.swiggyApp1.login.controller;

import com.swiggyapp1.swiggyApp1.exceptions.InvalidUserException;
import com.swiggyapp1.swiggyApp1.login.model.LoginCredentials;
import com.swiggyapp1.swiggyApp1.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login/authenticate")
public class UserLoginController {
  @Autowired UserLoginService userLoginService;

  @PostMapping("/credentials")
  public ResponseEntity<String> authenticateUserByCredentials(
      @RequestBody LoginCredentials loginCredentials) {
    try {
      userLoginService.authenticateUserByCredentials(loginCredentials);
      return ResponseEntity.ok("Valid User ");
    } catch (InvalidUserException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User");
    }
  }

  @PostMapping("/otp")
  public ResponseEntity<Void> sendOtpToUser(@RequestBody LoginCredentials loginCredentials) {
    userLoginService.sendOtpToMobile(loginCredentials);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/validateOtp")
  public ResponseEntity<String> validateOtp(@RequestBody LoginCredentials loginCredentials) {
    userLoginService.validateOtp(loginCredentials);
    return ResponseEntity.ok("Mobile Login Successful");
  }
}
