package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.Embeddable;

@Embeddable
public class InformationHotel {
    private Integer totalBookings;
    private Double reviews;

    public InformationHotel() {
    }
}
