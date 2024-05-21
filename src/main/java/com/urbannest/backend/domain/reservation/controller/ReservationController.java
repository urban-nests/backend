package com.urbannest.backend.domain.reservation.controller;

import com.urbannest.backend.domain.reservation.dto.ReservationRequest;
import com.urbannest.backend.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationService.toggleReservation(reservationRequest);
        return ResponseEntity.ok().build();
    }
}
