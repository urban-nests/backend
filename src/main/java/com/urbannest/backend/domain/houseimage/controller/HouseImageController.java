package com.urbannest.backend.domain.houseimage.controller;

import com.urbannest.backend.domain.houseimage.service.HouseImageService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HouseImageController {

    public final HouseImageService houseImageService;

    @Value("${urban-nest.image.max-cnt}")
    private int maxCnt;

    @PostMapping("/image")
    public ResponseEntity<?> uploadHouseImage(
            @RequestParam("dealId") Long dealId,
            @RequestPart("homeImages") List<MultipartFile> homeImages) {

        if (homeImages.size() > maxCnt) {
            throw new IllegalArgumentException("Too many home images");
        }

        houseImageService.saveHouseImage(dealId, homeImages);

        return ResponseEntity.ok().build();
    }
}
