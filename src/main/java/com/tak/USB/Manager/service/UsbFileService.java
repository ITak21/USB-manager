package com.tak.USB.Manager.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsbFileService {
    public List<String> getUsbFileList(String usbDrivePath) {
        List<String> fileList = new ArrayList<>();
        File usbDrive = new File(usbDrivePath);

        if (usbDrive.exists() && usbDrive.isDirectory()) {
            exploreDirectory(usbDrive, fileList);
        }

        return fileList;
    }

    private void exploreDirectory(File directory, List<String> fileList) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                    fileList.add(file.getAbsolutePath());
                if (file.isDirectory()) {
                    fileList.add(file.getName() + "/");
                    exploreDirectory(file, fileList); // 재귀 호출로 하위 디렉토리 검색
//                    System.out.println(file.getAbsolutePath()); //폴더명만 나옴
                }
            }
        }
    }


}
