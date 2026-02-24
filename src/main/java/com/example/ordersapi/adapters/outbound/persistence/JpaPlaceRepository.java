package com.example.ordersapi.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPlaceRepository extends JpaRepository<PlaceEntity, Long> {
}
