package com.tak.USB.Manager.service;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }


    public List<ProgramEntity> getAllPrograms() {
        System.out.println(programRepository.findAll()); //출력값 확인
        return programRepository.findAll();

    }
}
