package com.tak.USB.Manager.service;

import com.tak.USB.Manager.entity.LogEntity;
import com.tak.USB.Manager.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }
    public List<LogEntity> loadLog(int uIndex) {
        System.out.println(uIndex);
        List<LogEntity> getLog = logRepository.findTop1ByuIndexOrderByLogIndexDesc(uIndex);

        return getLog;
    }
}
