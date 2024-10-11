package com.swiggyapp1.swiggyApp1.registration.repository;

import com.swiggyapp1.swiggyApp1.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.usertype = :usertype")
  List<User> findByUsertype(@Param("usertype") String usertype);

  User findByEmail(String email);
}
