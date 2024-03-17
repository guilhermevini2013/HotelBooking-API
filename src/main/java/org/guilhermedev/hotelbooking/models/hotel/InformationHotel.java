package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.Embeddable;

@Embeddable
public class InformationHotel {
    private Integer totalBookings;
    private Double totalEvaluations;

    public InformationHotel() {
    }
}