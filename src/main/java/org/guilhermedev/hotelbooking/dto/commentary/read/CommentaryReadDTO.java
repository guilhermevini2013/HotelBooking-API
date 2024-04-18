package org.guilhermedev.hotelbooking.dto.commentary.read;

import org.guilhermedev.hotelbooking.models.information.Commentary;

public record CommentaryReadDTO(
        String commentary,
        Double evaluation,
        String nameSender,
        String commentaryTime
) {
    public CommentaryReadDTO(Commentary entity) {
        this(entity.getCommentary(),entity.getEvaluation(),entity.getSender().getName(),entity.getCommentaryTime().toString());
    }
}
