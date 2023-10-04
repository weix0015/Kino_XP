package com.example.kino_xp.restController;

import com.example.kino_xp.Entity.Ticket;
import com.example.kino_xp.Entity.User;
import com.example.kino_xp.entity.Ticket;
import com.example.kino_xp.entity.User;
import com.example.kino_xp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@RestController
public class UserController
{
    @PostMapping("/login")
    public String doLogin(@RequestParam("email") String email, HttpSession session,
                          @RequestParam("password") String password, Model model)
    {
        // Retrieve user by email from the repository
        User user = UserRepository.findAllByEmail(email);

        if (user != null)
        {
            if (user.getPassword() != null && BCrypt.checkpw(password, user.getPassword()))
            {
                // If password matches, set attributes in session
                session.setAttribute("email", email);
                // Store hashed password in session
                return "redirect:/index";
            }
            else
            {
                // If password does not match, return to login page with error message
                model.addAttribute("error", "Invalid password");
                return "login";
            }
        }
        return "login";
    }

    @PostMapping("/createUser")
    public String createUser(Model model, HttpSession session,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("tickets")List<Ticket>,
                             @RequestParam("name") String name,
                             @RequestParam(value = "admin", defaultValue = "false") boolean admin)
    {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setTickets(tickets);
        newUser.setName(name);
        newUser.setAdmin(admin);

        UserRepository.addUser(newUser);
        boolean isAdmin = UserRepository.isAdmin(email);
        model.addAttribute("isAdmin", isAdmin);

        return "redirect:/adminPanel";
    }
}
