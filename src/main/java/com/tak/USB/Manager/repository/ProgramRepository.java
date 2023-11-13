package com.tak.USB.Manager.repository;

import com.tak.USB.Manager.entity.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Long> {
    List<ProgramEntity> findByUsbId(Integer usbId);
    List<ProgramEntity> findBypgNameContaining(String keyword);
}
