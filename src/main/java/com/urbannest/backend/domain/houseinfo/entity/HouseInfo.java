package com.urbannest.backend.domain.houseinfo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbannest.backend.domain.dongcode.entity.Dongcode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "houseinfo")
public class HouseInfo {

    @Id
    @Column(name = "apt_code")
    private Long aptCode;

    @Column(name = "build_year")
    private Integer buildYear;

    @Column(name = "road_name", length = 40)
    private String roadName;

    @Column(name = "road_name_bonbun", length = 5)
    private String roadNameBonbun;

    @Column(name = "road_name_bubun", length = 5)
    private String roadNameBubun;

    @Column(name = "road_name_seq", length = 2)
    private String roadNameSeq;

    @Column(name = "road_name_basement_code", length = 1)
    private String roadNameBasementCode;

    @Column(name = "road_name_code", length = 7)
    private String roadNameCode;

    @Column(name = "dong", length = 40)
    private String dong;

    @Column(name = "bonbun", length = 4)
    private String bonbun;

    @Column(name = "bubun", length = 4)
    private String bubun;

    @Column(name = "sigungu_code", length = 5)
    private String sigunguCode;

    @Column(name = "eubmyundong_code", length = 5)
    private String eubmyundongCode;

    @Column(name = "land_code", length = 1)
    private String landCode;

    @Column(name = "apartment_name", length = 40)
    private String apartmentName;

    @Column(name = "jibun", length = 10)
    private String jibun;

    @Column(name = "lng", length = 30)
    private String lng;

    @Column(name = "lat", length = 30)
    private String lat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dong_code", referencedColumnName = "dong_code", nullable = false)
    private Dongcode dongCode;

    @Builder
    private HouseInfo(Long aptCode, Integer buildYear, String roadName, String roadNameBonbun, String roadNameBubun, String roadNameSeq, String roadNameBasementCode, String roadNameCode, String dong, String bonbun, String bubun, String sigunguCode, String eubmyundongCode, String landCode, String apartmentName, String jibun, String lng, String lat, Dongcode dongCode) {
        this.aptCode = aptCode;
        this.buildYear = buildYear;
        this.roadName = roadName;
        this.roadNameBonbun = roadNameBonbun;
        this.roadNameBubun = roadNameBubun;
        this.roadNameSeq = roadNameSeq;
        this.roadNameBasementCode = roadNameBasementCode;
        this.roadNameCode = roadNameCode;
        this.dong = dong;
        this.bonbun = bonbun;
        this.bubun = bubun;
        this.sigunguCode = sigunguCode;
        this.eubmyundongCode = eubmyundongCode;
        this.landCode = landCode;
        this.apartmentName = apartmentName;
        this.jibun = jibun;
        this.lng = lng;
        this.lat = lat;
        this.dongCode = dongCode;
    }
}