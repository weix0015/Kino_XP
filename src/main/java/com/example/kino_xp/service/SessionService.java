package com.example.kino_xp.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  public boolean isLoggedIn(HttpSession session) {
    // Check if the user is logged in based on session attributes
    return session.getAttribute("email") != null;
  }

  public void loginUser(HttpSession session, String email) {
    // Set user email in the session upon successful login
    session.setAttribute("email", email);
  }

  public void logoutUser(HttpSession session) {
    // Remove user attributes from the session upon logout
    session.removeAttribute("email");
  }
}

