package org.guilhermedev.hotelbooking.models.information;

import jakarta.persistence.*;
import org.guilhermedev.hotelbooking.dto.commentary.insert.CommentaryCreateDTO;
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
    private LocalDate commentaryTime;

    public Commentary(CommentaryCreateDTO commentaryCreateDTO, Client senderFind) {
        this.commentary = commentaryCreateDTO.commentary();
        this.evaluation = commentaryCreateDTO.evaluation();
        this.sender = senderFind;
        this.commentaryTime = LocalDate.now();

    }

    public Long getId() {
        return id;
    }

    public String getCommentary() {
        return commentary;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public Client getSender() {
        return sender;
    }

    public LocalDate getCommentaryTime() {
        return commentaryTime;
    }

    public Commentary() {
    }
}
