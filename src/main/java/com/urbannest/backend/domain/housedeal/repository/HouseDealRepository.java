package com.urbannest.backend.domain.housedeal.repository;

import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {
}
