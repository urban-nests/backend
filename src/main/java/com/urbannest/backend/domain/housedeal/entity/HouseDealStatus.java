package com.urbannest.backend.domain.housedeal.entity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HouseDealStatus {
    AVAILABLE(0),
    PROGRESS(1),
    COMPLETED(2);

    private final int value;
    private static final Map<Integer, HouseDealStatus> valueMap =
            Stream.of(HouseDealStatus.values())
                    .collect(Collectors.toMap(status -> status.value, status -> status));

    HouseDealStatus(int value) {
        this.value = value;
    }

    public static HouseDealStatus fromValue(int value) {
        if (!valueMap.containsKey(value)) {
            throw new IllegalArgumentException("Invalid status value: " + value);
        }
        return valueMap.get(value);
    }
}
