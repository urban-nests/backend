package com.urbannest.backend.domain.reservation.repository;

import com.urbannest.backend.domain.reservation.entity.ReservationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Long> {
    Optional<ReservationDetail> findByReservationNoAndMemberNo(Long reservationId, Long memberId);
}
