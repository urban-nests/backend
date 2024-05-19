package com.urbannest.backend.domain.member.repository;

import com.urbannest.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
}
