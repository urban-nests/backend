package com.urbannest.backend.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.urbannest.backend.domain.member.entity.Member;
import com.urbannest.backend.domain.member.exception.MemberException;
import com.urbannest.backend.domain.member.jwt.JwtDB;
import com.urbannest.backend.domain.member.jwt.JwtGenerator;
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
	private final JwtGenerator jwtGenerator;

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
		log.info("MemberService.login() is called");
		Member entity = memberRepository.findByEmail(member.getEmail());

//		log.warn(member.toString());
//		log.warn(entity.toString());
		if (entity == null) {
			throw new MemberException("존재하지 않는 사용자");
		}

		if (!passwordEncoder.matches(member.getPassword(), entity.getPassword())) {
			throw new MemberException("비밀번호 불일치");
		}

		return entity;
	}

	@Override
	public JwtToken saveToken(Member member) {
		log.info("MemberService.saveToken() is called.");
		String accessToken = jwtGenerator.createAccessToken(member);
		String refreshToken = jwtGenerator.createRefreshToken(member);

		// 추후 Redis와 같은 TTL 설정 필요
		JwtDB.JwtDB.put(member.getEmail() + ":access-token", accessToken);
		JwtDB.JwtDB.put(member.getEmail() + ":refresh-token", refreshToken);
//		log.warn(JwtDB.JwtDB.get(member.getEmail()+":access-token"));
//		log.warn(JwtDB.JwtDB.get(member.getEmail()+":refresh-token"));

		return new JwtToken(accessToken, refreshToken);
	}
}
