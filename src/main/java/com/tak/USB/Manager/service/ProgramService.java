package com.tak.USB.Manager.service;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.entity.UsbEntity;
import com.tak.USB.Manager.repository.ProgramRepository;
import com.tak.USB.Manager.repository.UsbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;
    private final UsbRepository usbRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository, UsbRepository usbRepository) {

        this.programRepository = programRepository;
        this.usbRepository = usbRepository;
    }


    public List<Map<String, String>> getAllPrograms() {

        List<ProgramEntity> pList = programRepository.findAll();
        List<Map<String,String>> result = new ArrayList<>();

        for(ProgramEntity p: pList){
            Optional<UsbEntity> u = usbRepository.findById(p.getUsbId());
            if (u.isPresent()){
                Map<String, String> data = new HashMap<>();
                data.put("pname", p.getPgName());
                data.put("ureturn", String.valueOf(u.get().getUsbReturn()));
                result.add(data);
            }
        }
        return result;

    }
}
