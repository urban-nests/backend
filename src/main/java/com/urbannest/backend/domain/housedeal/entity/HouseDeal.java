package com.urbannest.backend.domain.housedeal.entity;

import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import com.urbannest.backend.domain.houseinfo.entity.HouseInfo;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "housedeal")
public class HouseDeal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @Column(name = "deal_amount")
    private String dealAmount;

    @Column(name = "deal_year")
    private Integer dealYear;

    @Column(name = "deal_month")
    private Integer dealMonth;

    @Column(name = "deal_day")
    private Integer dealDay;

    @Column(name = "area")
    private String area;

    @Column(name = "floor")
    private String floor;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "cancel_deal_type")
    private String cancelDealType;

    @Column(name = "customer_no")
    private Long customerNo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private HouseDealStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", referencedColumnName = "no", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_code", nullable = false)
    private HouseInfo houseInfo;

    @OneToMany(mappedBy = "houseDeal")
    List<HouseImage> houseImageList;

    @Builder
    private HouseDeal(Long no, String dealAmount, Integer dealYear, Integer dealMonth, Integer dealDay, String area, String floor, String content, String cancelDealType, Long customerNo, HouseDealStatus status, Member member, HouseInfo houseInfo) {
        this.no = no;
        this.dealAmount = dealAmount;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.area = area;
        this.floor = floor;
        this.content = content;
        this.cancelDealType = cancelDealType;
        this.customerNo = customerNo;
        this.status = status != null ? status : HouseDealStatus.AVAILABLE;
        this.member = member;
        this.houseInfo = houseInfo;
    }
}
