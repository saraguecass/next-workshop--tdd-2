package com.example.ordersapi.adapters.outbound.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "places")
public class PlaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private int codPostal;

    public PlaceEntity() {
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getCity() {return city;}

    public void setCity(String city) {this.city = city;}

    public int getCodPostal() {return codPostal;}

    public void setCodPostal(int codPostal) {this.codPostal = codPostal;}
}
