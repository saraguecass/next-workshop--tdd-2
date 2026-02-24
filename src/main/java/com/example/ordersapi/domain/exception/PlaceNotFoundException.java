package com.example.ordersapi.domain.exception;

public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(Long id) {
        super("Place not found: " + id);
    }
}
