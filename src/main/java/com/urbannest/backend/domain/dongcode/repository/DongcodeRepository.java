package com.urbannest.backend.domain.dongcode.repository;

import com.urbannest.backend.domain.dongcode.entity.Dongcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DongcodeRepository extends JpaRepository<Dongcode, String> {
}
