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
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/update-status")
    public ResponseEntity<Map<String, Object>> updateUsbReturn(@RequestBody Map<String, String> requestBody){
        String Uid= requestBody.get("Uid");
        String newStatus = requestBody.get("newStatus");

        List<UsbEntity> updatedUSBList = usbService.updateUsbReturn(Uid, newStatus);

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
    @GetMapping("/get-program-list")
    @ResponseBody
    public Map<String, Object> getProgramsForUSB(@RequestParam("usbIndex") int usbIndex) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<ProgramEntity> getPrograms = programService.getProgramsForUSB(usbIndex); // Replace with your service logic
            response.put("success", true);
            response.put("programs", getPrograms);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to fetch program list");
        }

        return response;
    }
    @GetMapping("/search")
    public String searchPrograms(@RequestParam("keyword") String keyword,Model model) {
        List<Map<String ,String >> searchlist = programService.searchPg(keyword);
        List<UsbEntity> usbs= usbService.getAllUsbs();
        model.addAttribute("usbs", usbs);
        model.addAttribute("programs", searchlist);

        return "index";
    }
}
