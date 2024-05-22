package com.urbannest.backend.domain.housedeal.dto;

import com.urbannest.backend.domain.houseinfo.entity.HouseInfo;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import com.urbannest.backend.domain.housedeal.entity.HouseDealStatus;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import com.urbannest.backend.domain.dongcode.entity.Dongcode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class HouseInfoDealRequest {
    // HouseDeal fields
    private Long no;
    private Long dealNo;
    private String dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String content;
    private String cancelDealType;
    private Long customerNo;
    private String status;

    // HouseInfo fields
    private Long aptCode;
    private Integer buildYear;
    private String roadName;
    private String roadNameBonbun;
    private String roadNameBubun;
    private String roadNameSeq;
    private String roadNameBasementCode;
    private String roadNameCode;
    private String dong;
    private String bonbun;
    private String bubun;
    private String sigunguCode;
    private String eubmyundongCode;
    private String landCode;
    private String apartmentName;
    private String jibun;
    private String lng;
    private String lat;
    private String dongCode;

    // Member fields
    private Long memberNo;


    // HouseImage fields
//    private List<HouseImageRequestDto> houseImageList;

    // Method to create HouseInfo entity
    public HouseInfo toHouseInfo(Dongcode dongCode) {
        return HouseInfo.builder()
                .aptCode(this.aptCode)
                .buildYear(this.buildYear)
                .roadName(this.roadName)
                .roadNameBonbun(this.roadNameBonbun)
                .roadNameBubun(this.roadNameBubun)
                .roadNameSeq(this.roadNameSeq)
                .roadNameBasementCode(this.roadNameBasementCode)
                .roadNameCode(this.roadNameCode)
                .dong(this.dong)
                .bonbun(this.bonbun)
                .bubun(this.bubun)
                .sigunguCode(this.sigunguCode)
                .eubmyundongCode(this.eubmyundongCode)
                .landCode(this.landCode)
                .apartmentName(this.apartmentName)
                .jibun(this.jibun)
                .lng(this.lng)
                .lat(this.lat)
                .dongCode(dongCode)
                .build();
    }

    // Method to create HouseDeal entity
    public HouseDeal toHouseDeal(HouseInfo houseInfo, Member member, List<HouseImage> houseImages) {
        return HouseDeal.builder()
                .no(this.no)
                .deal_no(this.dealNo)
                .dealAmount(this.dealAmount)
                .dealYear(this.dealYear)
                .dealMonth(this.dealMonth)
                .dealDay(this.dealDay)
                .area(this.area)
                .floor(this.floor)
                .content(this.content)
                .cancelDealType(this.cancelDealType)
                .customerNo(this.customerNo)
                .status(HouseDealStatus.valueOf(this.status))
                .member(member)
                .houseInfo(houseInfo)
                .houseImageList(houseImages)
                .hit(0L)
                .build();
    }

    // Method to create HouseImage entities
//    public List<HouseImage> toHouseImages(HouseDeal houseDeal) {
//        return houseImageList.stream().map(imageDto -> HouseImage.builder()
//                .originalName(imageDto.getOriginalName())
//                .fileName(imageDto.getFileName())
//                .filePath(imageDto.getFilePath())
//                .houseDeal(houseDeal)
//                .build()
//        ).collect(Collectors.toList());
//    }
}
