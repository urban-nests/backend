package com.urbannest.backend.domain.member.serive;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.exception.MemberException;
import com.urbannest.backend.domain.member.jwt.JwtToken;
import com.urbannest.backend.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void signup(Member member) {
		log.info("MemberService.signup() is called.");
		if (memberRepository.findByEmail(member.getEmail()) != null) {
			throw new MemberException("중복된 이메일");
		}

		String encryptedPassword = passwordEncoder.encode(member.getPassword());
		Member entity = Member.builder()
			.email(member.getEmail())
			.nickname(member.getNickname())
			.password(encryptedPassword)
			.build();

		try {
			memberRepository.save(entity);
		}
		catch (Exception e) {
			throw new MemberException("회원가입 실패");
		}
	}

	@Override
	public Member login(Member member) {
		return null;
	}

	@Override
	public JwtToken saveToken(Member member) {
		return null;
	}
}
