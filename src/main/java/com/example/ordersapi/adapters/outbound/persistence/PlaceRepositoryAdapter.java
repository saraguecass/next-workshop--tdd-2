package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Place;
import com.example.ordersapi.domain.repository.PlaceRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaceRepositoryAdapter implements PlaceRepositoryPort {

    private final JpaPlaceRepository jpaRepository;
    private final PlacePersistenceMapper mapper;

    public PlaceRepositoryAdapter(JpaPlaceRepository jpaRepository, PlacePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Place> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Place> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Place save(Place place) {
        PlaceEntity entity = mapper.toEntity(place);
        PlaceEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void delete(Place place) {
        PlaceEntity entity = mapper.toEntity(place);
        jpaRepository.delete(entity);
    }
}
