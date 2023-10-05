package com.example.kino_xp.converter;

import com.example.kino_xp.dto.UserDTO;
import com.example.kino_xp.dto.UserLoginDTO;
import com.example.kino_xp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
  public User toEntity(UserDTO userDTO) {
    return new User(
      userDTO.id(),
      userDTO.name(),
      userDTO.email(),
      userDTO.password(),
      userDTO.tickets(),
      userDTO.admin());
  }

  public UserDTO toDTO(User user) {
    return new UserDTO(
      user.getId(),
      user.getName(),
      user.getEmail(),
      user.getPassword(),
      user.getTickets(),
      user.isAdmin()
    );
  }

  public UserLoginDTO toUserLoginDTO(User user) {
    return new UserLoginDTO(
      user.getEmail(),
      user.getPassword()
    );
  }
}

