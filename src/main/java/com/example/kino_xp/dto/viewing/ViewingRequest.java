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
public class ViewingRequest {
    private LocalDateTime showTime;
    private Long hall;
    private LocalDateTime showEndTime;
    private List<Long> ticket_ids;
    private Long movie_id;

    public void copy(Viewing viewing){
        this.showTime = viewing.getShowTime();
        this.hall = viewing.getHall();
        this.showEndTime = viewing.getShowEndTime();
        this.ticket_ids = viewing.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        this.movie_id = viewing.getMovie().getId();
    }

    public Viewing toViewing(){
        Viewing viewing = new Viewing();
        copy(viewing);
        return viewing;
    }
}
