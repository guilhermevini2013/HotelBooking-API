package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.models.user.Client;

import java.time.LocalDate;

@Entity
public class Commentary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commentary;
    private Double evaluation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Client sender;
    private LocalDate commentaryTime = LocalDate.now();
}
