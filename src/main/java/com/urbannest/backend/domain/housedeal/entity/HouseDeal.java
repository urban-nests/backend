package com.urbannest.backend.domain.housedeal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbannest.backend.domain.houseimage.entity.HouseImage;
import com.urbannest.backend.domain.houseinfo.entity.HouseInfo;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

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

    @Column(name = "deal_no")
    private Long deal_no;

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

    @Column(name = "hit")
    private Long hit;

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

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "houseDeal", fetch = FetchType.LAZY)
    List<HouseImage> houseImageList;

    @Builder
    public HouseDeal(Long no, Long deal_no, String dealAmount, Integer dealYear, Integer dealMonth, Integer dealDay, String area, String floor, String content, Long hit, String cancelDealType, Long customerNo, HouseDealStatus status, Member member, HouseInfo houseInfo, List<HouseImage> houseImageList) {
        this.no = no;
        this.deal_no = deal_no;
        this.dealAmount = dealAmount;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.area = area;
        this.floor = floor;
        this.content = content;
        this.hit = hit;
        this.cancelDealType = cancelDealType;
        this.customerNo = customerNo;
        this.status = status;
        this.member = member;
        this.houseInfo = houseInfo;
        this.houseImageList = houseImageList;
    }
}
