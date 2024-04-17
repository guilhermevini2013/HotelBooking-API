package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordinate {
    private Double positionX;
    private Double positionY;

    public Coordinate() {
    }

    public Coordinate(Double positionX, Double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Double getPositionX() {
        return positionX;
    }

    public Double getPositionY() {
        return positionY;
    }
}
