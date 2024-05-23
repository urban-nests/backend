package com.urbannest.backend.domain.member.repository;

import java.util.Optional;

import com.urbannest.backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);
}
