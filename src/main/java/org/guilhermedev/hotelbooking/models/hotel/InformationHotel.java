package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.Embeddable;

@Embeddable
public class InformationHotel {
    private Integer totalBookings;
    private Double totalEvaluations;

    public InformationHotel() {
        totalBookings = 0;
        totalEvaluations = 0.0;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public Double getTotalEvaluations() {
        return totalEvaluations;
    }
}
