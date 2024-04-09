package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String bytes;
    @ManyToOne
    private Hotel hotel;

    public Image(String bytes) {
        this.bytes = bytes;
    }

    public Image() {
    }
}
