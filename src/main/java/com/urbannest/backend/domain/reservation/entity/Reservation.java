package com.urbannest.backend.domain.reservation.entity;

import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Set;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int maxCnt;

    @Column(nullable = false)
    private int curCnt = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deal_no", nullable = false)
    private HouseDeal houseDeal;

    @OneToMany(mappedBy = "reservation")
    private Set<ReservationDetail> reservationDetails;

    @Builder
    public Reservation(Long no, LocalDate date, int maxCnt, int curCnt, HouseDeal houseDeal, Set<ReservationDetail> reservationDetails) {
        this.no = no;
        this.date = date;
        this.maxCnt = maxCnt;
        this.curCnt = curCnt;
        this.houseDeal = houseDeal;
        this.reservationDetails = reservationDetails;
    }

    @Transactional
    public int increaseCnt() {
        if (maxCnt <= curCnt) {
            throw new RuntimeException("남은 자리가 없습니다.");
        }
        return ++curCnt;
    }

    @Transactional
    public int decreaseCnt() {
        if (0 >= curCnt) {
            throw new RuntimeException("예약자가 없는 예약입니다.");
        }
        return --curCnt;
    }
}
