package com.example.kino_xp.dto.user;

import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password;

    private List<Long> ticket_ids;

    private boolean admin;

    public User getUserEntity(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name)
                .email(userRequest.getEmail())
                .password(BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt()))
                .admin(userRequest.isAdmin())
                .build();
    }

    public void copyTo(User user){
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
    }

    public User toUser(){
        User user = new User();
        copyTo(user);
        return user;
    }
}
