package com.urbannest.backend.domain.dongcode.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "dongcode")
public class Dongcode {

    @Id
    @Column(name = "dong_code", length = 20)
    private String dongcode;

    @Column(name = "sido_name", length = 10)
    private String sidoName;

    @Column(name = "gugun_name", length = 10)
    private String gugunName;

    @Column(name = "dong_name", length = 10)
    private String dongName;

    @Builder
    private Dongcode(String dongcode, String sidoName, String gugunName, String dongName) {
        this.dongcode = dongcode;
        this.sidoName = sidoName;
        this.gugunName = gugunName;
        this.dongName = dongName;
    }
}
