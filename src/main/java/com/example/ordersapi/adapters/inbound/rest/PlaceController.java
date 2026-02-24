package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.PlaceRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.PlaceResponseDto;
import com.example.ordersapi.application.port.inbound.PlaceUseCase;
import com.example.ordersapi.domain.model.Place;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceUseCase useCase;
    private final PlaceRestMapper mapper = new PlaceRestMapper();

    public PlaceController(PlaceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<PlaceResponseDto> getAll() {
        return useCase.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResponseDto create(@RequestBody PlaceRequestDto request) {
        Place place = mapper.toDomain(request);
        Place created = useCase.create(place);
        return mapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public PlaceResponseDto getById(@PathVariable Long id) {
        Place place = useCase.getById(id);
        return mapper.toResponse(place);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}
