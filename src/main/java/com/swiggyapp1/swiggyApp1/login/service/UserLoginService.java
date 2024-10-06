package com.swiggyapp1.swiggyApp1.login.service;

import com.swiggyapp1.swiggyApp1.exceptions.InvalidUserException;
import com.swiggyapp1.swiggyApp1.login.model.LoginCredentials;
import com.swiggyapp1.swiggyApp1.login.repository.UserLoginRepository;
import com.swiggyapp1.swiggyApp1.registration.entity.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
  @Autowired UserLoginRepository userLoginRepository;
  @Autowired OtpService otpService;
  @Autowired SmsService smsService;

  public User authenticateUserByCredentials(LoginCredentials loginCredentials) {
    return userLoginRepository
        .findByUsernameAndPassword(loginCredentials.getUname(), loginCredentials.getPassword())
        .orElseThrow(() -> new InvalidUserException("Invalid username or password"));
  }

  public void sendOtpToMobile(LoginCredentials loginCredentials) {
    Optional<User> findUserByMobileumber =
        userLoginRepository.findByMobilenumber(loginCredentials.getMobilenumber());
    if (findUserByMobileumber.isPresent()) {
      String otp = otpService.generateOtp(loginCredentials.getMobilenumber());
      smsService.sendSms(loginCredentials.getMobilenumber(), otp);
    }
  }

  public void validateOtp(LoginCredentials loginCredentials) {
    Optional<User> findUserByMobileumber =
        userLoginRepository.findByMobilenumber(loginCredentials.getMobilenumber());
    if (findUserByMobileumber.isPresent()) {
      otpService.validateOtp(loginCredentials.getMobilenumber(), loginCredentials.getOtp());
    }
  }
}
