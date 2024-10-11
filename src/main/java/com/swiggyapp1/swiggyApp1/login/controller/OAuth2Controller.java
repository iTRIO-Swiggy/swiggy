package com.swiggyapp1.swiggyApp1.login.controller;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.repository.UserRepository;
import com.swiggyapp1.swiggyApp1.security.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuth2Controller {

  @Autowired private CustomOAuth2UserService customOAuth2UserService;

  @Autowired private UserRepository userRepository;

  @GetMapping("/oauth2/success")
  public String getSuccessPage(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
    // Extract user attributes
    customOAuth2UserService.loadUser(new OAuth2UserRequest(null, (OAuth2AccessToken) oAuth2User));

    return "success"; // Return the name of the view (e.g., success.html)
  }
}
