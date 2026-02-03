package com.example.ordersapi.adapters.inbound.rest;

import com.example.ordersapi.adapters.inbound.rest.dto.OrderRequestDto;
import com.example.ordersapi.adapters.inbound.rest.dto.OrderResponseDto;
import com.example.ordersapi.application.port.inbound.OrderUseCase;
import com.example.ordersapi.domain.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderUseCase useCase;
    private final OrderRestMapper mapper = new OrderRestMapper();

    public OrderController(OrderUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<OrderResponseDto> getAll() {
        return useCase.getAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto create(@RequestBody OrderRequestDto request) {
        Order order = mapper.toDomain(request);
        Order created = useCase.create(order);
        return mapper.toResponse(created);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getById(@PathVariable Long id) {
        Order order = useCase.getById(id);
        return mapper.toResponse(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        useCase.delete(id);
    }
}
