package com.example.kino_xp.service;

import com.example.kino_xp.entity.User;
import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.repository.UserRepository;
import com.example.kino_xp.converter.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final UserConverter userConverter;


  @Autowired
  public UserService(UserRepository userRepository, UserConverter userConverter) {
    this.userConverter = userConverter;
    this.userRepository = userRepository;
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return users.stream()
      .map(userConverter::toDTO)
      .collect(Collectors.toList());
  }

  public List<UserDTO> getUsersByName(String name) {
    List<User> userList = userRepository.findAllByName(name);

    if (userList.isEmpty()) {
      throw new UserNotFoundException("No user found with name: " + name);
    } else {
      return userList.stream()
        .map(userConverter::toDTO)
        .collect(Collectors.toList());
    }
  }

  public UserDTO getUserById(int id) {
    Optional<User> optionalUser = userRepository.findById(id);

    if (optionalUser.isEmpty()) {
      throw new UserNotFoundException("User with id: " + id + " could not be found");
    } else {
      return userConverter.toDTO(optionalUser.get());
    }
  }

  public UserDTO getUserByEmail(String email) {
    List<User> userList = userRepository.findAllByEmail(email);
    if (!userList.isEmpty()) {
      User foundUser = userList.get(0);
      return userConverter.toDTO(foundUser);
    } else {
      throw new UserNotFoundException("User with email: " + email + " could not be found");
    }
  }

  public UserDTO createUser(UserDTO userDTO) {
    User userToSave = userConverter.toEntity(userDTO);
    userToSave.setId(0);

    String hashedPassword = BCrypt.hashpw(userDTO.password(), BCrypt.gensalt());

    userToSave.setPassword(hashedPassword);
    User savedUser = userRepository.save(userToSave);
    return userConverter.toDTO(savedUser);
  }

  public UserDTO updateUser(int id, UserDTO userDTO) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isEmpty()) {
      throw new UserNotFoundException("User with id: " + id + " not found");
    } else {
      User userToUpdate = userConverter.toEntity(userDTO);
      userToUpdate.setId(id);
      User savedUser = userRepository.save(userToUpdate);
      return userConverter.toDTO(savedUser);
    }
  }

  public void deleteUserById(int id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new UserNotFoundException("User with id: " + id + " not found");
    } else {
      userRepository.deleteById(id);
    }
  }
}
