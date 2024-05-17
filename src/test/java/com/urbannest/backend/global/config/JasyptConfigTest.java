package com.urbannest.backend.global.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    private StringEncryptor encryptor;

    @Test
    @DisplayName("origin string의 암,복호화 결과를 확인한다.")
    void stringEncryptor() {

        String originString = "string";

        // 암호화
        String encryptedString = encryptor.encrypt(originString);
        System.out.println("Encrypt Result >> ");
        System.out.println("ENC(" + encryptedString + ")");

        System.out.println();

        // 복호화
        String decryptedString = encryptor.decrypt(encryptedString);
        System.out.println("Decrypt Result >> ");
        System.out.println(decryptedString);

        Assertions.assertThat(originString).isEqualTo(decryptedString);
    }
}