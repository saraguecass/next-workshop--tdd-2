package com.example.ordersapi.domain.repository;

import com.example.ordersapi.domain.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceRepositoryPort {
    List<Place> findAll();

    Optional<Place> findById(Long id);

    Place save(Place place);

    void delete(Place place);
}
