package com.urbannest.backend.domain.houseimage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "houseimage")
public class HouseImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @Column(name = "original_name", length = 100)
    private String originalName;

    @Column(name = "file_name", length = 200)
    private String fileName;

    @Column(name = "file_path", length = 100)
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_no")
    @JsonIgnore
    private HouseDeal houseDeal;

    @Builder
    private HouseImage(Long no, String originalName, String fileName, String filePath, HouseDeal houseDeal) {
        this.no = no;
        this.originalName = originalName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.houseDeal = houseDeal;
    }
}