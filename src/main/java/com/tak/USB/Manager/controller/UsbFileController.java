package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.service.UsbFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsbFileController {
    private final UsbFileService usbFileService;

    @Autowired
    public UsbFileController(UsbFileService usbFileService) {
        this.usbFileService = usbFileService;
    }

    @GetMapping("/usb-files")
    public List<String> getUsbFiles(@RequestParam String usbDrivePath) {
        return usbFileService.getUsbFileList(usbDrivePath);
    }
}
