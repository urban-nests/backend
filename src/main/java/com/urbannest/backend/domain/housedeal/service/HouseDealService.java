package com.urbannest.backend.domain.housedeal.service;

import com.urbannest.backend.domain.housedeal.dto.HouseDealResponse;
import com.urbannest.backend.domain.housedeal.dto.HouseDealSummary;
import com.urbannest.backend.domain.housedeal.dto.HouseInfoDealRequest;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HouseDealService {
    List<HouseDealSummary> getHouseDeals(Long lastId, Pageable pageable);

    HouseDeal getHouseDeal(Long no);

    HouseDealResponse createHouseInfoDeal(HouseInfoDealRequest houseInfoDealRequest);
}
