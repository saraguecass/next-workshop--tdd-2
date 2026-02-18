package com.example.ordersapi.adapters.inbound.rest.dto;

public class PlaceRequestDto {
    private String name;
    private String city;
    private int codPostal;

    public String getName() {return name;}

    public String getCity() {return city;}

    public int getCodPostal() {return codPostal;}

    public void setName(String name) {this.name = name;}

    public void setCity(String city) {this.city = city;}

    public void setCodPostal(int codPostal) {this.codPostal = codPostal;}
}
