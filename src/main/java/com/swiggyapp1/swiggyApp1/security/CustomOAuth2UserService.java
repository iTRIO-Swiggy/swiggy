package com.swiggyapp1.swiggyApp1.security;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private RestTemplate restTemplate; // Make sure to define a RestTemplate bean

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    String provider = userRequest.getClientRegistration().getRegistrationId();
    String providerId = oAuth2User.getName();
    String email = oAuth2User.getAttribute("email");
    String name = oAuth2User.getAttribute("name");
    String picture = oAuth2User.getAttribute("picture");
    String firstName = oAuth2User.getAttribute("given_name");
    String lastName = oAuth2User.getAttribute("family_name");
    BigInteger bigIntId = new BigInteger(providerId);

    // Check if the user already exists
    User user = userRepository.findByEmail(email);
    if (user == null) {
      // New user registration
      user = new User();
      user.setFname(firstName);
      user.setLname(lastName);
      user.setPassword("NA");
      user.setId(bigIntId);
      user.setEmail(email);
      user.setUsername(name);
      user.setProfilepicture(picture);
      user.setProvider(provider);
      userRepository.save(user);
    }

    // Check if the mobile number is null
    if (user.getId() != null && user.getMobilenumber() == null) {
      String mobileRequestUrl = "http://localhost:8080/users/mobile/request";
      String mobileNumber = null;

      try {
        String uri =
            UriComponentsBuilder.fromHttpUrl(mobileRequestUrl)
                .queryParam("userId", user.getId())
                .queryParam("email", email)
                .queryParam("mobilenumber", "9030704763")
                .toUriString();

        // Make the GET request
        ResponseEntity<String> response = restTemplate.postForEntity(uri, null, String.class);

        // Handle the response
        mobileNumber = response.getBody();
      } catch (RestClientException e) {
        System.out.println("Error calling mobile request endpoint: " + e.getMessage() + e);
      }

      // Save the mobile number to the user
      if (mobileNumber != null) {
        user.setMobilenumber(mobileNumber);
        userRepository.save(user);
      }
    }

    return oAuth2User;
  }
}
