package com.urbannest.backend.domain.reservation.service;

import com.urbannest.backend.domain.reservation.dto.ReservationRequest;
import com.urbannest.backend.domain.reservation.entity.Reservation;
import com.urbannest.backend.domain.reservation.repository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class ReservationServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImplTest.class);
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @Transactional
    void toggleReservation() throws InterruptedException {
        int threadCount = 50;
        ReservationRequest req = new ReservationRequest(
                LocalDate.now(),
                13L,
                3L
        );

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger hitCount = new AtomicInteger(0);
        AtomicInteger missCount = new AtomicInteger(0);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    reservationService.toggleReservation(req);
                    hitCount.incrementAndGet();
                } catch (Exception e) {
                    missCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        log.info("hitCount = {}", hitCount);
        log.info("missCount = {}", missCount);

        Optional<Reservation> reservation = reservationRepository.findByDateAndHouseDealNo(req.date(), req.dealId());
        Assertions.assertThat(reservation.isPresent()).isTrue();
    }
}
