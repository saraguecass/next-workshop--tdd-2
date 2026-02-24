package com.example.ordersapi.application.port.inbound;

import com.example.ordersapi.domain.model.Place;
import java.util.List;

public interface PlaceUseCase {

    List<Place> getAll();

    Place create(Place place);

    Place getById(Long id);

    void delete(Long id);
}
