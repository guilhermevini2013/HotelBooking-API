package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinate {
    private Double positionX;
    private Double positionY;

    public Coordinate() {
    }
}
