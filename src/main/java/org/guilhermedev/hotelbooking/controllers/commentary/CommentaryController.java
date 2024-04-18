package org.guilhermedev.hotelbooking.controllers.commentary;

import org.apache.coyote.Response;
import org.guilhermedev.hotelbooking.dto.commentary.insert.CommentaryCreateDTO;
import org.guilhermedev.hotelbooking.services.action.CommentaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/commentary")
public class CommentaryController {
    private final CommentaryService commentaryService;

    public CommentaryController(CommentaryService commentaryService) {
        this.commentaryService = commentaryService;
    }
    @PostMapping
    public ResponseEntity<Void> addCommentary(@RequestBody CommentaryCreateDTO commentaryCreateDTO){
        commentaryService.addCommentary(commentaryCreateDTO);
        return ResponseEntity.noContent().build();
    }
}
