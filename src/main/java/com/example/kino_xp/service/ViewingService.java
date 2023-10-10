package com.example.kino_xp.service;

import com.example.kino_xp.dto.viewing.ViewingRequest;
import com.example.kino_xp.dto.viewing.ViewingResponse;
import com.example.kino_xp.exception.ViewingNotFoundException;
import com.example.kino_xp.model.Movie;
import com.example.kino_xp.model.Viewing;
import com.example.kino_xp.repository.MovieRepository;
import com.example.kino_xp.repository.ViewingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ViewingService {
    private final ViewingRepository viewingRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    public ViewingService(ViewingRepository viewingRepository) {
        this.viewingRepository = viewingRepository;
    }

    public List<ViewingResponse> getAllViewings() {
        List<Viewing> viewings = viewingRepository.findAll();
        return viewings.stream()
                .map(ViewingResponse::new).toList();
    }

    public ViewingResponse getViewingById(Long id) {
        Optional<Viewing> optionalViewing = viewingRepository.findById(id);
        Viewing viewing = optionalViewing.get();
        if (optionalViewing.isPresent()) {
            return new ViewingResponse(viewing);
        } else {
            throw new ViewingNotFoundException("Viewing with id: "
            + id
            + " not found");
        }
    }

    public ViewingResponse createViewing(ViewingRequest viewingRequest) {
        Viewing viewingToSave = viewingRequest.getViewingEntity(viewingRequest);
        viewingToSave.setMovie(findMovie(viewingRequest));
        Viewing savedViewing = viewingRepository.save(viewingToSave);
        return new ViewingResponse(savedViewing);
    }

    public void deleteViewingById(Long id) {
        Optional<Viewing> viewing = viewingRepository.findById(id);
        if (viewing.isPresent()) {
            viewingRepository.deleteById(id);
        } else {
            throw new ViewingNotFoundException("Viewing with id: "
            + id
            + " not found");
        }
    }

    public ViewingResponse updateViewing (Long id, ViewingRequest viewingRequest) {
        Optional<Viewing> viewingToEditOptional = viewingRepository.findById(id);
        Viewing viewingToEdit = viewingToEditOptional.get();
        if(viewingToEditOptional.isPresent()){
            viewingRequest.copyTo(viewingToEdit);
            return new ViewingResponse(viewingRepository.save(viewingToEdit));
        } else {
            throw new ViewingNotFoundException("Viewing with id: "
            + id
            + " could not be found");
        }
    }

    public Movie findMovie(ViewingRequest viewingRequest){
        return movieRepository.findById(viewingRequest.getMovieTitle())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "movie with this id is unknown"));
    }
}
