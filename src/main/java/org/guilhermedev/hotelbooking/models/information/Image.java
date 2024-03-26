package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] bytes;

    public Image(byte[] bytes) {
        this.bytes = bytes;
    }

    public Image() {

    }
}
