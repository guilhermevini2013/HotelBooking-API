package org.guilhermedev.hotelbooking.services.hotel;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Component
public class ImageService {

    public byte[] transformBase64(MultipartFile image) throws IOException {
        byte[] encodeImage = Base64.getEncoder().encode(image.getBytes());
        return encodeImage;
    }
}
