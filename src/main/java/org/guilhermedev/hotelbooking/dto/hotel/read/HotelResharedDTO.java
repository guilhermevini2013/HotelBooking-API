package org.guilhermedev.hotelbooking.dto.hotel.read;

import org.guilhermedev.hotelbooking.models.hotel.Hotel;

public class HotelResharedDTO {
    final private Long id;
    final private String name;
    final private Integer totalBookings;
    final private Double totalEvaluations;
    final private Double price;
    final private String city;


    public HotelResharedDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.totalBookings = hotel.getInformationHotel().getTotalBookings();
        this.totalEvaluations = hotel.getInformationHotel().getTotalEvaluations();
        this.city = hotel.getAddress().getCity();
        this.price = hotel.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public Double getTotalEvaluations() {
        return totalEvaluations;
    }

    public String getCity() {
        return city;
    }
}
