package com.urbannest.backend.domain.housedeal.dto;

import com.urbannest.backend.domain.housedeal.dto.projection.DealIdWithMemberProjection.SimpleMemberProjection;
import com.urbannest.backend.domain.housedeal.dto.projection.SimpleHouseDealProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.urbannest.backend.domain.housedeal.dto.projection.SimpleHouseDealProjection.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HouseDealSummary {
    private LocalDateTime createdAt;
    private Long no;
    private Long dealNo;
    private String dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String content;
    private String status;
    private HouseInfoProjection houseInfo;
    private List<HouseImageProjection> houseImageList;
    private SimpleMemberProjection member;

    public HouseDealSummary(SimpleHouseDealProjection hd, SimpleMemberProjection m) {
        this.createdAt = hd.getCreatedAt();
        this.no = hd.getNo();
        this.dealNo = hd.getDeal_no();
        this.dealAmount = hd.getDealAmount();
        this.dealYear = hd.getDealYear();
        this.dealMonth = hd.getDealMonth();
        this.dealDay = hd.getDealDay();
        this.area = hd.getArea();
        this.floor = hd.getFloor();
        this.content = hd.getContent();
        this.status = hd.getStatus();
        this.houseInfo = hd.getHouseInfo();
        this.houseImageList = hd.getHouseImageList();
        this.member = m;

    }
}
