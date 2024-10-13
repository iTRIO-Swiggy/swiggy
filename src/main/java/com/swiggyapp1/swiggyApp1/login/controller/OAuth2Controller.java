package com.swiggyapp1.swiggyApp1.login.controller;

import com.swiggyapp1.swiggyApp1.security.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

  @Autowired private CustomOAuth2UserService customOAuth2UserService;

  @Autowired private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

  @Autowired private OAuth2AuthorizedClientService oAuth2AuthorizedClientService; // Add this line

  @GetMapping("/oauth2/success")
  public String getSuccessPage(OAuth2AuthenticationToken authentication) {
    String registrationId = authentication.getAuthorizedClientRegistrationId();
    OAuth2AuthorizedClient authorizedClient =
        oAuth2AuthorizedClientService.loadAuthorizedClient(
            registrationId, authentication.getName());

    OAuth2UserRequest userRequest =
        new OAuth2UserRequest(
            authorizedClient.getClientRegistration(), authorizedClient.getAccessToken());
    customOAuth2UserService.loadUser(userRequest);
    return "User successfully authenticated: ";
  }
}
