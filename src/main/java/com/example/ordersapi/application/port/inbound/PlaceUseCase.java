package com.example.ordersapi.application.port.inbound;
import java.util.List;

import com.example.ordersapi.domain.model.Place;

public interface PlaceUseCase {
    List<Place> getAll();

    Place create(Place place);

    Place getById(Long id);

    void delete(Long id);
}
