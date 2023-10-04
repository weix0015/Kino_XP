package com.example.kino_xp.restController;

import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.dto.UserLoginDTO;
import com.example.kino_xp.entity.User;
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
public class UserController
{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<String> doLogin(Model model, HttpSession session,
                                          @RequestBody UserLoginDTO userLoginDTO) {
        if (sessionService.isLoggedIn(session)) {
            // User is already logged in, handle accordingly
            // You can return an error response or redirect to another page
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            // Handle other unexpected exceptions here
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO){
        UserDTO user = userService.createUser(userDTO);
        if (user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User creation failed");
        }
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        try{
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
