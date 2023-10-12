package com.example.kino_xp.dto.viewing;

import com.example.kino_xp.model.Ticket;
import com.example.kino_xp.model.Viewing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewingResponse {
    private Long id;
    private LocalDateTime showTime;
    private Long hall;
    private LocalDateTime showEndTime;
    private List<Long> ticket_ids;
    private String movieTitle;

    public ViewingResponse(Viewing viewing){
        this.id = viewing.getId();
        this.showTime = viewing.getShowTime();
        this.hall = viewing.getHall();
        this.showEndTime = viewing.getShowEndTime();
        this.ticket_ids = viewing.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        this.movieTitle = viewing.getMovie().getTitle();
    }
}
