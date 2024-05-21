package com.urbannest.backend.global.util;

import com.urbannest.backend.domain.houseimage.exception.ImageExtensionNotSupportedException;
import com.urbannest.backend.domain.houseimage.exception.ImageSizeOutOfBoundException;
import com.urbannest.backend.domain.houseimage.exception.ImageUploadFailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

@Component
public class ImageFileValidator {

    private final Set<String> ALLOWED_EXTENSIONS = Set.of("jpeg", "jpg", "png");

    @Value("${urban-nest.image.max-size}")
    private int MAX_FILE_SIZE;

    public void validateFileExtension(String fileExt) {
        if (fileExt == null || !ALLOWED_EXTENSIONS.contains(fileExt.toLowerCase())) {
            throw new ImageExtensionNotSupportedException("Unsupported file extension: " + fileExt);
        }
    }

    public void validateFileSize(MultipartFile file) {
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ImageSizeOutOfBoundException("File size exceeds limit: " + file.getSize());
        }
    }

    public void validateFileContent(MultipartFile file) {
        try {
            String mimeType = file.getContentType();
            if (mimeType == null || !mimeType.startsWith("image/")) {
                throw new ImageUploadFailException("Invalid image content");
            }
        } catch (Exception e) {
            throw new ImageUploadFailException("Failed to validate image content: " + e.getMessage());
        }
    }
}
