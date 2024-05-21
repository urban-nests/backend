package com.urbannest.backend.domain.reservation.service;

import com.urbannest.backend.domain.reservation.dto.ReservationRequest;
import com.urbannest.backend.domain.reservation.entity.Reservation;

import java.time.LocalDate;

public interface ReservationService {

    void toggleReservation(ReservationRequest reservationRequest);
}
