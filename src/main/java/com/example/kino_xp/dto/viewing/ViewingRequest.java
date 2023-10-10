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
    private Long id;
    private LocalDateTime showTime;
    private Long hall;
    private LocalDateTime showEndTime;
    private List<Long> ticket_ids;
    private String movieTitle;

    public void copyTo(Viewing viewing) {
        viewing.setId(id);
    }


    public Viewing toViewing() {
        Viewing viewing = new Viewing();
        copyTo(viewing);
        return viewing;
    }

    public Viewing getViewingEntity(ViewingRequest v) {
        return Viewing.builder()
                .id(v.id)
                .showTime(v.showTime)
                .hall(v.hall)
                .showEndTime(v.showEndTime)
                .build();
    }
}
