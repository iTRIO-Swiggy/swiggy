package com.swiggyapp1.swiggyApp1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
                    authorizeRequests ->
                            authorizeRequests
                                    .requestMatchers("/", "/oauth2/**", "/users/**")
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated())
            .oauth2Login(
                    oauth2Login ->
                            oauth2Login
                                    .defaultSuccessUrl("/oauth2/success", true)
                                    .failureUrl("/login?error=true"));
    return http.build();
  }

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    return new InMemoryClientRegistrationRepository(
            Arrays.asList(googleClientRegistration(), facebookClientRegistration()));
  }

  private ClientRegistration googleClientRegistration() {
    return ClientRegistration.withRegistrationId("google")
            .clientId("427014481836-lh7kj12at8c45jmfar5im8c328tufait.apps.googleusercontent.com")
            .clientSecret("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")
            .scope("openid", "profile", "email")
            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
            .tokenUri("https://oauth2.googleapis.com/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName("sub")
            .clientName("Google")
            .redirectUri("http://localhost:8080/login/oauth2/code/google")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .build();
  }

  private ClientRegistration facebookClientRegistration() {
    return ClientRegistration.withRegistrationId("facebook")
            .clientId("3950478065187206")
            .clientSecret("c3077c0ce0be4b88964efdca4c8af8be")
            .scope("public_profile", "email")
            .authorizationUri("https://www.facebook.com/v11.0/dialog/oauth")
            .tokenUri("https://graph.facebook.com/v11.0/oauth/access_token")
            .userInfoUri("https://graph.facebook.com/me?fields=id,name,email")
            .userNameAttributeName("id")
            .clientName("Facebook")
            .redirectUri("http://localhost:8080/login/oauth2/code/facebook")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .build();
  }
}
