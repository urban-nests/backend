package com.urbannest.backend.domain.houseimage.service;

import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import com.urbannest.backend.domain.houseimage.exception.ImageUploadFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalImageHandler implements ImageHandler {

    @Value("${urban-nest.image.save-path.local}")
    private String savePath;

    @Override
    public HouseImage saveImage(MultipartFile file) {

        String fileExt = getFileExtension(file.getOriginalFilename());
        String normalizedFileName = getNormalizedFileName(removeFileExtension(file.getOriginalFilename(), true));
        String generatedFileName = generateFileName();

        try {
            file.transferTo(new File(savePath + File.separator + generatedFileName + "." + fileExt));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ImageUploadFailException(normalizedFileName + " upload fail");
        }

        return HouseImage.builder()
                .originalName(normalizedFileName)
                .filePath(savePath)
                .fileName(generatedFileName)
                .extension(fileExt)
                .build();
    }
}
