package com.swiggyapp1.swiggyApp1.login.repository;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoginRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findByMobilenumber(String mobilenumber);
}
