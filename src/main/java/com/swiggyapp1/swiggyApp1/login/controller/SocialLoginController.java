package com.swiggyapp1.swiggyApp1.login.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class SocialLoginController {

    // Endpoint to start OAuth2 login for a given provider (Google/Facebook)
    @GetMapping("/login/oauth2/{provider}")
    public void loginWithSocial(@PathVariable String provider, HttpServletResponse response) throws IOException {
        String redirectUrl = "/oauth2/authorization/" + provider;
        response.sendRedirect(redirectUrl);  // Spring Security handles the OAuth2 flow from here
    }
}


