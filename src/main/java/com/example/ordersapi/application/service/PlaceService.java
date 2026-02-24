package com.example.ordersapi.application.service;

import com.example.ordersapi.application.port.inbound.PlaceUseCase;
import com.example.ordersapi.domain.exception.PlaceNotFoundException;
import com.example.ordersapi.domain.model.Place;
import com.example.ordersapi.domain.repository.PlaceRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService implements PlaceUseCase {

    private final PlaceRepositoryPort repository;

    public PlaceService(PlaceRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Place> getAll() {
        return repository.findAll();
    }

    @Override
    public Place create(Place place) {
        return repository.save(place);
    }

    @Override
    public Place getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        Place place = repository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
        repository.delete(place);
    }
}
