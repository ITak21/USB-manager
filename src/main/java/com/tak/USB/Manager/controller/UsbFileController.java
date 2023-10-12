package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.repository.ProgramRepository;
import com.tak.USB.Manager.service.UsbFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller
public class UsbFileController {
    private final UsbFileService usbFileService;

    @Autowired
    public UsbFileController(UsbFileService usbFileService, ProgramRepository programRepository) {
        this.usbFileService = usbFileService;
        this.programRepository=programRepository;
    }
    private ProgramRepository programRepository;

    @GetMapping("/usb-files")
    public String getUsbFiles(@RequestParam String usbDrivePath, Model model) {
        List<String> fileList = usbFileService.getUsbFileList(usbDrivePath);
        model.addAttribute("usbFiles", fileList);
        if(fileList.isEmpty()) {
            model.addAttribute("errorMessage", "usb를 연결해주세요");
            return "errorPage";
        }
        return "usb-files";
    }

    @PostMapping("/save")
    public String savePrograms(@RequestParam List<String> selectedPrograms,@RequestParam int index){
        for(String programName : selectedPrograms){
            ProgramEntity sprograms = new ProgramEntity();
            sprograms.setPgName(programName);
            sprograms.setUsbId(index);
            System.out.println(programName);
            programRepository.save(sprograms);
        }
        return "redirect:/";
    }
}
