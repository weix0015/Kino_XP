package com.example.kino_xp.service;

import com.example.kino_xp.converter.ViewingConverter;
import com.example.kino_xp.dto.ViewingDTO;
import com.example.kino_xp.model.Viewing;
import com.example.kino_xp.repository.ViewingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ViewingService {
    private final ViewingRepository viewingRepository;

    private final ViewingConverter viewingConverter;

    @Autowired
    public ViewingService(ViewingRepository viewingRepository, ViewingConverter viewingConverter, ViewingConverter viewingConverter1) {
        this.viewingRepository = viewingRepository;
        this.viewingConverter = viewingConverter;
    }

    public List<ViewingDTO> getAllViewings() {
        List<ViewingDTO> viewings = viewingRepository.findAll().stream().
                map(viewingConverter::viewingToDTO).
                collect(Collectors.toList());
        return viewings;
    }

    public ViewingDTO getViewingById(int id) {
        Optional<Viewing> optionalViewing = viewingRepository.findById(id);
        if (optionalViewing.isPresent()) {
            return viewingConverter.viewingToDTO(optionalViewing.get());
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ViewingDTO createViewing(ViewingDTO viewingDTO) {
        Viewing viewingToSave = viewingConverter.toEntity(viewingDTO);
        viewingToSave.setId(0);
        Viewing savedViewing = viewingRepository.save(viewingToSave);
        return viewingConverter.viewingToDTO(savedViewing);
    }

    public void deleteViewingById(int id) {
        Optional<Viewing> viewing = viewingRepository.findById(id);
        if (viewing.isPresent()) {
            viewingRepository.deleteById(id);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public ViewingDTO updateViewing (int id, ViewingDTO viewingDTO) {
        Optional<Viewing> existingViewing = viewingRepository.findById(id);
        if(existingViewing.isPresent()){
            Viewing viewingToUpdate = viewingConverter.toEntity(viewingDTO);
            viewingToUpdate.setId(id);
            Viewing savedViewing = viewingRepository.save(viewingToUpdate);
            return viewingConverter.viewingToDTO(savedViewing);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
