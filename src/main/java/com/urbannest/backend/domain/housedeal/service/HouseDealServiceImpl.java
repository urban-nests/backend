package com.urbannest.backend.domain.housedeal.service;

import com.urbannest.backend.domain.housedeal.dto.HouseDealSummary;
import com.urbannest.backend.domain.housedeal.dto.projection.DealIdWithMemberProjection;
import com.urbannest.backend.domain.housedeal.dto.projection.DealIdWithMemberProjection.SimpleMemberProjection;
import com.urbannest.backend.domain.housedeal.dto.projection.SimpleHouseDealProjection;
import com.urbannest.backend.domain.housedeal.entity.HouseDeal;
import com.urbannest.backend.domain.housedeal.repository.HouseDealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseDealServiceImpl implements HouseDealService{

    private final HouseDealRepository houseDealRepository;

    @Override
    @Transactional
    public List<HouseDealSummary> getHouseDeals(Long lastId, Pageable pageable) {
        List<SimpleHouseDealProjection> houseDeals = houseDealRepository.findHouseDealsAfterId(lastId, pageable);

        List<Long> dealIds = getDealIds(houseDeals);
        List<DealIdWithMemberProjection> members = houseDealRepository.findMembersByDealIds(dealIds);

        return getHouseDealSummaries(houseDeals, members);
    }

    @Override
    @Transactional
    public HouseDeal getHouseDeal(Long no) {
        houseDealRepository.incrementHit(no);

        return houseDealRepository.findById(no)
                .orElseThrow(() ->  new EntityNotFoundException("deal_no: " + no + " not found"));
    }

    private static List<HouseDealSummary> getHouseDealSummaries(List<SimpleHouseDealProjection> houseDeals, List<DealIdWithMemberProjection> members) {
        Map<Long, SimpleMemberProjection> memberMap = getMemberMap(members);

        return houseDeals.stream()
                .map(hd -> new HouseDealSummary(hd, memberMap.get(hd.getNo())))
                .collect(Collectors.toList());
    }

    private static List<Long> getDealIds(List<SimpleHouseDealProjection> houseDeals) {
        return houseDeals.stream().map(SimpleHouseDealProjection::getNo).collect(Collectors.toList());
    }

    private static Map<Long, SimpleMemberProjection> getMemberMap(List<DealIdWithMemberProjection> memberList) {
        return memberList.stream()
                .collect(Collectors.toMap(DealIdWithMemberProjection::getNo, DealIdWithMemberProjection::getMember));
    }
}

