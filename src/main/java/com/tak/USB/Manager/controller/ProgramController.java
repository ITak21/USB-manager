package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProgramController {
    private final ProgramService programService;
    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    // 모든 프로그램 목록을 반환
    @GetMapping("/")
    public String getAllPrograms(Model model) {
        List<ProgramEntity> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "index";
    }
}
