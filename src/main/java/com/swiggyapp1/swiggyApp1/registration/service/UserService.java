package com.swiggyapp1.swiggyApp1.registration.service;

import com.swiggyapp1.swiggyApp1.exceptions.ResourceNotFoundException;
import com.swiggyapp1.swiggyApp1.registration.constant.UserRole;
import com.swiggyapp1.swiggyApp1.registration.entity.User;
import com.swiggyapp1.swiggyApp1.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        UserRole userRole = UserRole.valueOf(user.getUsertype());
        user.setUsertype(userRole.getCode());
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByType(String userType) {
        return userRepository.findByUsertype(userType);
    }

    public User updateUser(User user) {
        Optional<User> userById = userRepository.findById(user.getId());
        if (userById.isPresent()) {
            UserRole userRole = UserRole.valueOf(user.getUsertype());
            user.setUsertype(userRole.getCode());
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException("unknown user id");
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
