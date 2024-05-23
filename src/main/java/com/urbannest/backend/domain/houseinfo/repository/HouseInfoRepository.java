package com.urbannest.backend.domain.houseinfo.repository;

import com.urbannest.backend.domain.houseinfo.entity.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
}
