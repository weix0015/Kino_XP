package com.example.kino_xp.dto.user;

import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password = null;

    private List<Long> ticket_ids;

    private boolean admin;

    public void copy(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.ticket_ids = user.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        this.admin = user.isAdmin();
    }

    public User toUser(){
        User user = new User();
        copy(user);
        return user;
    }
}
