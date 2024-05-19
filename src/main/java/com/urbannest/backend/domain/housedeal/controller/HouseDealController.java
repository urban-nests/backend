package com.urbannest.backend.domain.housedeal.controller;

import com.urbannest.backend.domain.housedeal.dto.HouseDealSummary;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import com.urbannest.backend.domain.housedeal.service.HouseDealService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HouseDealController {

    private final HouseDealService houseDealService;

    @GetMapping("/housedeals")
    public ResponseEntity<?> getHouseDeals(
            @RequestParam(name = "lastId", defaultValue = Long.MAX_VALUE+"") Long lastId,
            @PageableDefault(page = 0, size = 10, sort = "no", direction = Sort.Direction.DESC) Pageable pageable) {

        List<HouseDealSummary> houseDealPage = houseDealService.getHouseDeals(lastId, pageable);
        return ResponseEntity.ok(houseDealPage);
    }

    @GetMapping("/housedeal/{no}")
    public ResponseEntity<?> getHouseDeal(@PathVariable("no") Long no) {
        HouseDeal houseDeal = houseDealService.getHouseDeal(no);
        return ResponseEntity.ok(houseDeal);
    }
}
