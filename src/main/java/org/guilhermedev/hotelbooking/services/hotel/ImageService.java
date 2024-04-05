package org.guilhermedev.hotelbooking.services.hotel;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
public class ImageService {

    public String transformBase64(MultipartFile image) throws IOException {
        String encodeImage = Base64.getEncoder().encodeToString(image.getBytes());
        return encodeImage;
    }
}
