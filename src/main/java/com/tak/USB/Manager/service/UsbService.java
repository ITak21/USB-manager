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
    public List<UsbEntity> updateUsbReturn(String Uid, String newStatus){
        int pUid = Integer.valueOf(Uid);
        int nStat= Integer.valueOf(newStatus);
        List<UsbEntity> usbList = usbRepository.findById(pUid);
        if(usbList != null && !usbList.isEmpty()) {
            for (UsbEntity  usbEntity: usbList){
                usbEntity.setUsbReturn(nStat);
            }
            usbRepository.saveAll(usbList);
        }
        return usbList;
    }
    public boolean addUsb(String usbName) {

        try {
            UsbEntity usb = new UsbEntity();
            usb.setUsbName(usbName);
            usb.setUsbReturn(0);
            usbRepository.save(usb);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
