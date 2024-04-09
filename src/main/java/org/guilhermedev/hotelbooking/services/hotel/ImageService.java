package org.guilhermedev.hotelbooking.services.hotel;

import org.guilhermedev.hotelbooking.models.information.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ImageService {

    public Set<Image> transformBase64(List<MultipartFile> images) {
        return images.stream().map(image -> {
            try {
                return new Image(Base64.getEncoder().encodeToString(image.getBytes()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());
    }
}
