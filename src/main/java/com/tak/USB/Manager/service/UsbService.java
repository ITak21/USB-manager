package com.tak.USB.Manager.service;

import com.tak.USB.Manager.entity.UsbEntity;
import com.tak.USB.Manager.repository.UsbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsbService {
    private final UsbRepository usbRepository;

    @Autowired
    public UsbService(UsbRepository usbRepository){
        this.usbRepository = usbRepository;
    }
    public List<UsbEntity> getAllUsbs(){
//        System.out.println(usbRepository.findAll());
        return usbRepository.findAll();
    }

}
