//package com.swiggyapp1.swiggyApp1.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//
//import java.util.Arrays;
//
//@Configuration
//public class SecurityConfig {
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(csrf -> csrf.ignoringRequestMatchers("/swagger-ui/**", "/v3/api-docs/**")) // Ensure CSRF is ignored for Swagger
//            .authorizeHttpRequests(authorizeRequests ->
//                    authorizeRequests
//                            .requestMatchers("/", "/oauth2/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Permit access to these endpoints
//                            .anyRequest().authenticated() // All other endpoints require authentication
//            )
//            .oauth2Login(oauth2Login ->
//                    oauth2Login
//                            .defaultSuccessUrl("/oauth2/success", true)
//                            .failureUrl("/login?error=true")
//            );
//
//    return http.build();
//  }
//
//  @Bean
//  public ClientRegistrationRepository clientRegistrationRepository() {
//    return new InMemoryClientRegistrationRepository(
//            Arrays.asList(googleClientRegistration(), facebookClientRegistration()));
//  }
//
//  private ClientRegistration googleClientRegistration() {
//    return ClientRegistration.withRegistrationId("google")
//            .clientId("your-google-client-id")
//            .clientSecret("your-google-client-secret")
//            .scope("openid", "profile", "email")
//            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//            .tokenUri("https://oauth2.googleapis.com/token")
//            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//            .userNameAttributeName("sub")
//            .clientName("Google")
//            .redirectUri("http://localhost:8080/login/oauth2/code/google")
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .build();
//  }
//
//  private ClientRegistration facebookClientRegistration() {
//    return ClientRegistration.withRegistrationId("facebook")
//            .clientId("your-facebook-client-id")
//            .clientSecret("your-facebook-client-secret")
//            .scope("public_profile", "email")
//            .authorizationUri("https://www.facebook.com/v11.0/dialog/oauth")
//            .tokenUri("https://graph.facebook.com/v11.0/oauth/access_token")
//            .userInfoUri("https://graph.facebook.com/me?fields=id,name,email")
//            .userNameAttributeName("id")
//            .clientName("Facebook")
//            .redirectUri("http://localhost:8080/login/oauth2/code/facebook")
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .build();
//  }
//}
