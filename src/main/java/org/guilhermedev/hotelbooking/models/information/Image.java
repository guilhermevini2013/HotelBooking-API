package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String bytes;

    public Image(String bytes) {
        this.bytes = bytes;
    }

    public Image() {

    }
}
