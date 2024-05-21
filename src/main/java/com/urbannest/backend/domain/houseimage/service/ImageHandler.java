package com.urbannest.backend.domain.houseimage.service;

import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.text.Normalizer;
import java.util.UUID;

public interface ImageHandler {

    HouseImage saveImage(MultipartFile imageFile);

    default String removeFileExtension(String filename, boolean removeAllExtensions) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }
        String extPattern = "(?<!^)[.]" + (removeAllExtensions ? ".*" : "[^.]*$");
        return filename.replaceAll(extPattern, "");
    }

    default String getNormalizedFileName(String filename) {
        return Normalizer.normalize(filename, Normalizer.Form.NFC);
    }

    /**
     * get file extension without '.'
     * @return "test.png" => "png"
     */
    default String getFileExtension(String filename) {
        return StringUtils.getFilenameExtension(filename);
    }

    default String generateFileName() {
        return System.currentTimeMillis() + "_" + UUID.randomUUID();
    }

}
