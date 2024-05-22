package com.urbannest.backend.domain.houseimage.service;

import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import com.urbannest.backend.domain.housedeal.repository.HouseDealRepository;
import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import com.urbannest.backend.domain.houseimage.repository.HouseImageRepository;
import com.urbannest.backend.global.util.ImageFileValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseImageServiceImpl implements HouseImageService{

    private final HouseImageRepository houseImageRepository;
    private final HouseDealRepository houseDealRepository;
    private final ImageHandler imageHandler;
    private final ImageFileValidator validator;

    @Transactional
    @Override
    public void saveHouseImage(Long dealId, List<MultipartFile> files) {
        HouseDeal houseDeal = houseDealRepository.findById(dealId)
                .orElseThrow(() -> new EntityNotFoundException("can't find house deal"));

        files.forEach(file -> {
            validator.validateFileSize(file);
            validator.validateFileExtension(imageHandler.getFileExtension(file.getOriginalFilename()));
            validator.validateFileContent(file);
        });

        // TODO: insert 쿼리가 이미지마다 각각 나감 -> 한번에 처리 가능한지?
        List<HouseImage> houseImageList = files.stream()
                .map(file -> imageHandler.saveImage(file))
                .peek(houseImage -> houseImage.setHouseDeal(houseDeal))
                .toList();

        houseImageRepository.saveAll(houseImageList);
    }
}
