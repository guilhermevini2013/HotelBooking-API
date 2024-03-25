package org.guilhermedev.hotelbooking.dto.hotel.insert;

import org.guilhermedev.hotelbooking.models.hotel.SizeType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public record RoomCreateDTO(Double price,
                            Set<MultipartFile> Images,
                            SizeType sizeRoom) {
}
