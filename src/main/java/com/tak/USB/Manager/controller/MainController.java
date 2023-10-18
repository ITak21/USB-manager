package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.entity.UsbEntity;
import com.tak.USB.Manager.service.ProgramService;
import com.tak.USB.Manager.service.UsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final ProgramService programService;
    private final UsbService usbService;
    @Autowired
    public MainController(ProgramService programService, UsbService usbService) {
        this.programService = programService;
        this.usbService = usbService;
    }

    // 모든 프로그램 목록을 반환
    @GetMapping("/")
    public String getAllList(Model model) {
        List<UsbEntity> usbs =usbService.getAllUsbs();
        List<Map<String ,String >> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        model.addAttribute("usbs", usbs);
        return "index";
    }
}
