package com.tak.USB.Manager.repository;

import com.tak.USB.Manager.entity.UsbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsbRepository extends JpaRepository<UsbEntity, Integer> {
    List<UsbEntity> findById(int usbIndex);

}