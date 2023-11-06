package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.entity.UsbEntity;
import com.tak.USB.Manager.service.ProgramService;
import com.tak.USB.Manager.service.UsbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
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
    @PostMapping("/update-program-status")
    public ResponseEntity<Map<String, Object>> updateUsbReturn(@RequestBody Map<String, String> requestBody){
        String programUid= requestBody.get("programUid");
        String newStatus = requestBody.get("newStatus");

        List<UsbEntity> updatedUSBList = usbService.updateUsbReturn(programUid, newStatus);

        if (updatedUSBList != null && !updatedUSBList.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "상태가 업데이트되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", "USB를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
