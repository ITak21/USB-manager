package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.service.UsbFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller
public class UsbFileController {
    private final UsbFileService usbFileService;

    @Autowired
    public UsbFileController(UsbFileService usbFileService) {
        this.usbFileService = usbFileService;
    }

    @GetMapping("/usb-files")
    public String getUsbFiles(@RequestParam String usbDrivePath, Model model) {
        List<String> fileList = usbFileService.getUsbFileList(usbDrivePath);
        model.addAttribute("usbFiles", fileList);
        return "usb-files";
    }
}
