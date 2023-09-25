package com.tak.USB.Manager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tak.USB.Manager.service.UsbFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsbFileServiceTest {

    @Mock
    private UsbFileService usbFileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsbFileList() {
        // 테스트할 USB 드라이브 경로 설정
        String usbDrivePath = "D:/"; // 실제 경로에 따라 수정

        // 실제 파일 및 폴더가 있는지 확인하여 테스트 수행
        File usbDrive = new File(usbDrivePath);
        if (usbDrive.exists() && usbDrive.isDirectory()) {
            List<String> fileList = usbFileService.getUsbFileList(usbDrivePath);

            // 예상되는 결과 목록과 실제 결과 목록 비교
            List<String> expectedList = getExpectedUsbFileList(usbDrive);
            assertEquals(expectedList, fileList);
        }
    }

    private List<String> getExpectedUsbFileList(File directory) {
        List<String> expectedList = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    expectedList.add(file.getName());
                } else if (file.isDirectory()) {
                    expectedList.add(file.getName() + "/");
                    expectedList.addAll(getExpectedUsbFileList(file));
                }
            }
        }

        return expectedList;
    }
}
