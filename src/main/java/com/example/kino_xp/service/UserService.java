package com.example.kino_xp.service;

import com.example.kino_xp.dto.user.UserRequest;
import com.example.kino_xp.dto.user.UserResponse;
import com.example.kino_xp.model.User;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TicketService ticketService;


    @Autowired
    public UserService(UserRepository userRepository, TicketService ticketService) {
        this.userRepository = userRepository;
        this.ticketService = ticketService;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getUsersByName(String name) {
        List<User> userList = userRepository.findAllByName(name);

        if (userList.isEmpty()) {
            throw new UserNotFoundException("No user found with name: " + name);
        } else {
            return userList.stream()
                    .map(UserResponse::new)
                    .collect(Collectors.toList());
        }
    }

    public UserResponse getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " could not be found");
        } else {
            return new UserResponse (user);
        }
    }

    public UserResponse getUserByEmail(String email) {
        List<User> userList = userRepository.findAllByEmail(email);
        if (!userList.isEmpty()) {
            User foundUser = userList.get(0);
            return new UserResponse(foundUser);
        } else {
            throw new UserNotFoundException("User with email: " + email + " could not be found");
        }
    }

    public UserResponse createUser(UserRequest userRequest) {
        User userToSave = new User();
        if (userRepository.existsByEmail(userRequest.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email: "
            + userRequest.getEmail()
            + " already exists");
        } else {
            userRequest.copyTo(userToSave);
            userRepository.save(userToSave);
            return new UserResponse(userToSave);
        }
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        User existingUser = new User();
        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        } else {
            existingUser = existingUserOptional.get();
            userRequest.copyTo(existingUser);
            return new UserResponse(userRepository.save(existingUser));
        }
    }

    public void deleteUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        } else {
            userRepository.deleteById(id);
        }
    }
}
