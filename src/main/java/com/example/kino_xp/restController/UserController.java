package com.example.kino_xp.restController;

import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.dto.UserLoginDTO;
import com.example.kino_xp.entity.Ticket;
import com.example.kino_xp.entity.User;
import com.example.kino_xp.repository.UserRepository;
import com.example.kino_xp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> doLogin(Model model, HttpSession session,
                                                @RequestBody UserLoginDTO userLoginDTO)
    {
        // Retrieve user by email from the repository
        List<User> userListByEmail = userRepository.findAllByEmail(userLoginDTO.email());
        User user = userListByEmail.get(0);
        if (user != null)
        {
            if (user.getPassword() != null && BCrypt.checkpw(userLoginDTO.password(), user.getPassword()))
            {
                // If password matches, set attributes in session
                session.setAttribute("email", userLoginDTO.email());
                // Store hashed password in session
                return ResponseEntity.status(HttpStatus.OK).body(userLoginDTO);
            }
            else
            {
                // If password does not match, return to login page with error message
                model.addAttribute("error", "Invalid password");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userLoginDTO);
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



    /*
    @PostMapping("/user")
    public ResponseEntity<String> createUser(Model model, HttpSession session,
                                             @RequestBody UserDTO userDTO) {
        try {
            String email = userDTO.email();
            String password = userDTO.password();
            List<Ticket> tickets = userDTO.tickets();
            String name = userDTO.name();
            boolean admin = userDTO.admin();

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);
            newUser.setTickets(tickets);
            newUser.setName(name);
            newUser.setAdmin(admin);

            userRepository.save(newUser);

            return ResponseEntity.ok("User created successfully");
        } catch (DataIntegrityViolationException e) {
            // Handle any data integrity violation, such as duplicate email
            return ResponseEntity.badRequest().body("Email already exists");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
         */

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> putUser(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
