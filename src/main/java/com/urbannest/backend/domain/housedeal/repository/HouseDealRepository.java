package com.urbannest.backend.domain.housedeal.repository;

import com.urbannest.backend.domain.housedeal.dto.projection.DealIdWithMemberProjection;
import com.urbannest.backend.domain.housedeal.dto.projection.SimpleHouseDealProjection;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HouseDealRepository extends JpaRepository<HouseDeal, Long> {

    @Query("select hd from HouseDeal hd join fetch hd.houseInfo hi where hd.no <= :id")
    List<SimpleHouseDealProjection> findHouseDealsAfterId(@Param("id") Long id, Pageable pageable);

    @Query("select hd from HouseDeal hd join fetch hd.member m where hd.no in :dealIdList")
    List<DealIdWithMemberProjection> findMembersByDealIds(@Param("dealIdList") List<Long> dealIdList);

    @Query("select hd from HouseDeal hd join fetch hd.houseInfo hi join fetch hd.member m join fetch hi.dongCode where hd.no = :id ")
    Optional<HouseDeal> findById(@Param("id") Long id);
}
