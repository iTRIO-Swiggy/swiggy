package com.swiggyapp1.swiggyApp1.login.service;

import com.swiggyapp1.swiggyApp1.exceptions.InvalidUserException;
import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MobileDetailsService {
  @Autowired UserRepository userRepository;

  public void updateMobileNumber(String email, String mobileNumber) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new InvalidUserException("The User with the given email doesn't exist");
    }
    user.setMobilenumber(mobileNumber);
    userRepository.save(user);
  }
}
