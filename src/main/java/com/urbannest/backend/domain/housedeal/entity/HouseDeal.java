package com.urbannest.backend.domain.housedeal.entity;

import com.urbannest.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "status", columnDefinition = "int default 0")
    private Integer status; // ENUM으로 변경?? 0:거래중, 거래완료, 거래취소

    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @Column(name = "apt_code", nullable = false)
    private Long aptCode;

    @Builder
    private HouseDeal(Long no, String dealAmount, Integer dealYear, Integer dealMonth, Integer dealDay, String area, String floor, String content, String cancelDealType, Long customerNo, Integer status, Long memberNo, Long aptCode) {
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
        this.status = status;
        this.memberNo = memberNo;
        this.aptCode = aptCode;
    }

    // Getters and Setters
}