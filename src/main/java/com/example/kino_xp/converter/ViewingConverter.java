package com.example.kino_xp.converter;

import com.example.kino_xp.dto.ViewingDTO;
import com.example.kino_xp.model.Viewing;
import org.springframework.stereotype.Component;

@Component
public class ViewingConverter {
    public ViewingDTO viewingToDTO(Viewing viewing) {
        return new ViewingDTO(
                viewing.getId(),
                viewing.getShowTime(),
                viewing.getHall(),
                viewing.getShowEndTime(),
                viewing.getTicket(),
                viewing.getMovie()
        );
    }

    public Viewing toEntity(ViewingDTO viewingDTO) {
        return new Viewing(
                viewingDTO.id(),
                viewingDTO.showTime(),
                viewingDTO.hall(),
                viewingDTO.showEndTime(),
                viewingDTO.ticket(),
                viewingDTO.movie()
        );
    }
}
