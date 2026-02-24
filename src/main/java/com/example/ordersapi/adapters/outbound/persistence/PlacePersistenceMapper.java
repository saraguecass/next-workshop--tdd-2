package com.example.ordersapi.adapters.outbound.persistence;

import com.example.ordersapi.domain.model.Place;
import org.springframework.stereotype.Component;

@Component
public class PlacePersistenceMapper {

    public PlaceEntity toEntity(Place domain) {
        if (domain == null) {
            return null;
        }

        PlaceEntity entity = new PlaceEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCity(domain.getCity());
        entity.setCodPostal(domain.getCodPostal());
        return entity;
    }

    public Place toDomain(PlaceEntity entity) {
        if (entity == null) {
            return null;
        }

        Place domain = new Place();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setCity(entity.getCity());
        domain.setCodPostal(entity.getCodPostal());
        return domain;
    }
}
