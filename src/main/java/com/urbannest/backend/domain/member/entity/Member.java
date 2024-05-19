package com.urbannest.backend.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbannest.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Long no;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
//    @JsonIgnore
    private String password;

    @Column(nullable = false, length = 30)
    private String nickname;

    @JsonIgnore
    private LocalDateTime deletedAt;


    @Builder
    private Member(Long no, String email, String password, String nickname) {
        this.no = no;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
