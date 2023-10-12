package com.tak.USB.Manager.repository;

import com.tak.USB.Manager.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
}
