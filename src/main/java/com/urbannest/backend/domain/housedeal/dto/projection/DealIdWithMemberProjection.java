package com.urbannest.backend.domain.housedeal.dto.projection;

public interface DealIdWithMemberProjection {
    Long getNo();
    SimpleMemberProjection getMember();

    interface SimpleMemberProjection {
        Long getNo();
        String getNickname();
    }
}
