package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Embeddable
public class Coordinate {
    private Double positionX;
    private Double positionY;

    public Coordinate() {
    }
}
