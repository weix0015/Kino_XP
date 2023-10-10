package com.example.kino_xp.restController;

import com.example.kino_xp.dto.user.UserRequest;
import com.example.kino_xp.dto.user.UserResponse;
import com.example.kino_xp.model.User;
import com.example.kino_xp.exception.UserNotFoundException;
import com.example.kino_xp.repository.UserRepository;
import com.example.kino_xp.service.SessionService;
import com.example.kino_xp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@RestController

public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserService userService;

  @Autowired
  SessionService sessionService;

  @GetMapping("/users")
  public ResponseEntity<List<UserResponse>> getUsers() {
    List<UserResponse> userResponses = userService.getAllUsers();
    return new ResponseEntity<>(userResponses, HttpStatus.OK);
  }

  @GetMapping("/user/id/{id}")
  public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
    UserResponse userResponse = userService.getUserById(id);
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }

  @GetMapping("/user/email/{email}")
  public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("email") String email) {
    UserResponse userResponse = userService.getUserByEmail(email);
    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }

  @GetMapping("/user/{name}")
  public ResponseEntity<List<UserResponse>> getUsersByName(@PathVariable("name") String name) {
    List<UserResponse> userResponses = userService.getUsersByName(name);
    return new ResponseEntity<>(userResponses, HttpStatus.OK);
  }

/*
  @PostMapping("/login")
  public ResponseEntity<String> doLogin(Model model, HttpSession session,
                                        @RequestBody UserLoginDTO userLoginDTO) {
    if (sessionService.isLoggedIn(session)) {
      // User is already logged in
      return ResponseEntity.status(HttpStatus.CONFLICT).body("User is already logged in");
    }

    // Retrieve user by email from the repository
    List<User> userListByEmail = userRepository.findAllByEmail(userLoginDTO.email());

    try {
      User user = userListByEmail.get(0);

      if (user.getPassword() != null && BCrypt.checkpw(userLoginDTO.password(), user.getPassword())) {
        // If password matches, set attributes in session
        sessionService.loginUser(session, userLoginDTO.email());
        return ResponseEntity.status(HttpStatus.OK).body("Login successful");
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
      }
    } catch (IndexOutOfBoundsException e) {
      // Catch the exception when the user is not found in the database
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + userLoginDTO.email());
    } catch (Exception e) {
      // Handle other unexpected exceptions here
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<String> doLogout(HttpSession session) {
    session.invalidate();
    return ResponseEntity.status(HttpStatus.OK).body("Logout successful");
  }

 */


  @PostMapping("/user")
  public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
    UserResponse userResponse = userService.createUser(userRequest);
    if (userResponse != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
    }
  }


  @PutMapping("/user/{id}")
  public ResponseEntity<UserResponse> putUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
    return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
    try {
      userService.deleteUserById(id);
      return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    } catch (UserNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
