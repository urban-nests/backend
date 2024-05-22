package com.urbannest.backend.domain.reservation.service;

import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import com.urbannest.backend.domain.housedeal.repository.HouseDealRepository;
import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.repository.MemberRepository;
import com.urbannest.backend.domain.reservation.dto.ReservationRequest;
import com.urbannest.backend.domain.reservation.entity.Reservation;
import com.urbannest.backend.domain.reservation.entity.ReservationDetail;
import com.urbannest.backend.domain.reservation.repository.ReservationDetailRepository;
import com.urbannest.backend.domain.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final ReservationDetailRepository detailRepository;
    private final HouseDealRepository houseDealRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public synchronized void toggleReservation(ReservationRequest req) {
        Reservation reservation =
                reservationRepository.findByDateAndHouseDealNo(req.date(), req.dealId())
                        .orElseGet(() -> addReservation(req));

        Optional<ReservationDetail> detail =
                detailRepository.findByReservationNoAndMemberNo(reservation.getNo(), req.memberId());
        if (detail.isPresent()) {
            log.info("Reservation already exists");
            reservation.decreaseCnt();
            detailRepository.delete(detail.get());
            return;
        }

        reservation.increaseCnt();
        Member memberRef = memberRepository.getReferenceById(req.memberId());
        ReservationDetail reservationDetail = ReservationDetail.builder()
                .reservation(reservation)
                .member(memberRef)
                .build();
        detailRepository.save(reservationDetail);
    }

    @Transactional
    protected Reservation addReservation(ReservationRequest req) {
        HouseDeal houseDealRef = houseDealRepository.getReferenceById(req.dealId());
        Reservation newReservation = Reservation.builder()
                .date(req.date())
                .houseDeal(houseDealRef)
                .maxCnt(10)
                .build();

        return reservationRepository.save(newReservation);
    }
}
