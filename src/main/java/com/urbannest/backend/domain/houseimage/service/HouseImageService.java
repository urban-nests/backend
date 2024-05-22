package com.urbannest.backend.domain.houseimage.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HouseImageService {
    void saveHouseImage(Long dealId, List<MultipartFile> files);
}
