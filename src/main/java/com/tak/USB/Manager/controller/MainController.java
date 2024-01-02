package com.tak.USB.Manager.controller;

import com.tak.USB.Manager.entity.LogEntity;
import com.tak.USB.Manager.entity.ProgramEntity;
import com.tak.USB.Manager.entity.UsbEntity;
import com.tak.USB.Manager.repository.LogRepository;
import com.tak.USB.Manager.service.LogService;
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
    private final LogService logService;
    private LogRepository logRepository;

    private Integer convertToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            // 형변환이 불가능한 경우, 예외가 발생하고 메시지를 출력합니다.
            System.out.println("형변환이 불가능한 값: " + value);
            return null;
        }
    }
    @Autowired
    public MainController(ProgramService programService, UsbService usbService, LogService logService, LogRepository logRepository) {
        this.programService = programService;
        this.usbService = usbService;
        this.logService = logService;
        this.logRepository = logRepository;
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
        String uid= requestBody.get("Uid");
        String newStatus = requestBody.get("newStatus");
        String pgid = requestBody.get("pgid");
        String rentName = requestBody.get("rentName");
        String rentGroup = requestBody.get("rentGroup");

        LogEntity saveLog = new LogEntity();

        if (convertToInt(newStatus)==1) {
            saveLog.setPIndex(convertToInt(pgid));
            saveLog.setUIndex(convertToInt(uid));
            saveLog.setRtName(rentName);
            saveLog.setRtGroup(rentGroup);

            logRepository.save(saveLog);
        }else {
            List<LogEntity> logList  = logRepository.findTop1ByuIndexOrderByLogIndexDesc(convertToInt(uid));
            if (!logList.isEmpty()) {
                LogEntity log = logList.get(0);
                log.setReturnDate(new java.sql.Date(System.currentTimeMillis())); // 현재 날짜로 업데이트
                logRepository.save(log);
            }

        }

        List<UsbEntity> updatedUSBList = usbService.updateUsbReturn(uid, newStatus);

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

    @GetMapping("/load-log")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loadLog(@RequestParam("uIndex") int uIndex) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<LogEntity> logs = logService.loadLog(uIndex); // Replace with your service logic
            response.put("success", true);
            response.put("logs", logs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", "Failed to fetch program list");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }


    }


    @GetMapping("/search")
    public String searchPrograms(@RequestParam("keyword") String keyword,Model model) {
        List<Map<String ,String >> searchlist = programService.searchPg(keyword);
        List<UsbEntity> usbs= usbService.getAllUsbs();
        model.addAttribute("usbs", usbs);
        model.addAttribute("programs", searchlist);

        return "index";
    }

    @PostMapping("/add-usb")
    public ResponseEntity<String> addUsb(@RequestParam String usbName) {
        // Call a service method to handle the addition of USB
        boolean success = usbService.addUsb(usbName);

        if (success) {
            return ResponseEntity.ok("USB added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add USB");
        }
    }
}
