package com.urbannest.backend.domain.houseimage.repository;

import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseImageRepository extends JpaRepository<HouseImage, Long> {
}
