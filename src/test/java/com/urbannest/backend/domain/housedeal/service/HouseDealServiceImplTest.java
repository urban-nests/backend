//package com.urbannest.backend.domain.housedeal.service;
//
//import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
//import com.urbannest.backend.domain.housedeal.repository.HouseDealRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//
//@SpringBootTest
////@Transactional TODO: Test 실행 후 DB 롤백
//class HouseDealServiceImplTest {
//
//    @Autowired
//    private HouseDealService houseDealService;
//
//    @Autowired
//    private HouseDealRepository houseDealRepository;
//
//    private HouseDeal testHouseDeal;
//    private long initialHit;
//
//    @BeforeEach
//    public void setUp() {
//        testHouseDeal = houseDealRepository.findById(1L).orElseThrow();
//        initialHit = testHouseDeal.getHit();
//    }
//
//    @Test
//    @DisplayName("동시성 이슈를 해결한 조회 테스트")
//    void getHouseDealConcurrencyTest() throws InterruptedException {
//        int threadCount = 100;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        CountDownLatch latch = new CountDownLatch(threadCount);
//
//        AtomicInteger hitCount = new AtomicInteger(0);
//        AtomicInteger missCount = new AtomicInteger(0);
//
//        for (int i = 0; i < threadCount; i++) {
//            executorService.execute(() -> {
//                try {
//                    houseDealService.getHouseDeal(testHouseDeal.getNo());
//                    hitCount.incrementAndGet();
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                    missCount.incrementAndGet();
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//        latch.await();
//
//        System.out.println("hitCount = " + hitCount);
//        System.out.println("missCount = " + missCount);
//
//        HouseDeal updatedHouseDeal = houseDealRepository.findById(testHouseDeal.getNo()).orElseThrow();
//        Assertions.assertThat(updatedHouseDeal.getHit()).isEqualTo(threadCount+initialHit);
//    }
//}
