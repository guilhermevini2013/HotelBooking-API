package org.guilhermedev.hotelbooking.models.hotel;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double price;
//    @Lob
//    private Set<byte[]> imagesRoom = new HashSet<>();
    private SizeType sizeRoom;

    public Room() {
    }
}
