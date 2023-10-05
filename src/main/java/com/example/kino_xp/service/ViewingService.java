package com.example.kino_xp.service;

import com.example.kino_xp.repository.ViewingRepository;
import org.springframework.stereotype.Service;

@Service
public class ViewingService {
    private final ViewingRepository viewingRepository;

    public ViewingService(ViewingRepository viewingRepository) {
        this.viewingRepository = viewingRepository;
    }

}
