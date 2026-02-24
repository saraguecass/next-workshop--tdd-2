package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.PlaceRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.PlaceResponseDto;
import com.example.ordersapi.domain.model.Place;

public class PlaceRestMapper {

    public Place toDomain(PlaceRequestDto dto) {
        Place place = new Place();
        place.setName(dto.getName());
        place.setCity(dto.getCity());
        place.setCodPostal(dto.getCodPostal());
        return place;
    }

    public PlaceResponseDto toResponse(Place place) {
        return new PlaceResponseDto(
                place.getId(),
                place.getName(),
                place.getCity(),
                place.getCodPostal()
        );
    }
}