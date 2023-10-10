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
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Long> ticket_ids;
    private boolean admin;

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.ticket_ids = user.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        this.admin = user.isAdmin();
    }
}
