package com.urbannest.backend.domain.housedeal.dto.projection;

import java.time.LocalDateTime;
import java.util.List;

public interface SimpleHouseDealProjection {
    LocalDateTime getCreatedAt();
    Long getNo();
    Long getDeal_no();
    String getDealAmount();
    Integer getDealYear();
    Integer getDealMonth();
    Integer getDealDay();
    String getArea();
    String getFloor();
    String getContent();
    String getStatus();
    HouseInfoProjection getHouseInfo();
    List<HouseImageProjection> getHouseImageList();

    interface HouseInfoProjection {
        Long getAptCode();
        Integer getBuildYear();
        String getRoadName();
        String getRoadNameBonbun();
        String getRoadNameBubun();
        String getRoadNameSeq();
        String getRoadNameBasementCode();
        String getDong();
        String getBonbun();
        String getBubun();
        String getSigunguCode();
        String getEubmyundongCode();
        String getLandCode();
        String getApartmentName();
        String getJibun();
        String getLng();
        String getLat();
    }

    interface HouseImageProjection {
        Long getNo();
        String getOriginalName();
        String getFileName();
        String getFilePath();

    }
}