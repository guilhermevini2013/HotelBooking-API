package org.guilhermedev.hotelbooking.services.action;

import jakarta.transaction.Transactional;
import org.guilhermedev.hotelbooking.dto.commentary.insert.CommentaryCreateDTO;
import org.guilhermedev.hotelbooking.models.hotel.Hotel;
import org.guilhermedev.hotelbooking.models.information.Commentary;
import org.guilhermedev.hotelbooking.models.user.Client;
import org.guilhermedev.hotelbooking.repositories.HotelRepository;
import org.guilhermedev.hotelbooking.services.exceptions.ResourceNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentaryService {
    private final HotelRepository hotelRepository;

    public CommentaryService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    public void addCommentary(CommentaryCreateDTO commentaryCreateDTO) {
        Client senderFind = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotelFound = hotelRepository.findById(commentaryCreateDTO.idHotel()).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelFound.calculateTotalEvaluations(commentaryCreateDTO.evaluation());
        hotelFound.getCommentaries().add(new Commentary(commentaryCreateDTO, senderFind));
    }
}
