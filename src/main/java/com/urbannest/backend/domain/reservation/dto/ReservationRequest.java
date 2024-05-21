package com.urbannest.backend.domain.reservation.dto;

import java.time.LocalDate;

public record ReservationRequest(LocalDate date, Long dealId, Long memberId) {
}
