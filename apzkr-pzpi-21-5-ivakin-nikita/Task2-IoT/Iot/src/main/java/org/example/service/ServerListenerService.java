package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.IoT;
import org.example.model.DeviceInfo;

import java.io.File;
import java.io.IOException;

public class ServerListenerService {

    public String saveData(String array[]){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(Integer.parseInt(array[0].split(":")[1]));
        deviceInfo.setLogin(array[1].split(":")[1]);
        deviceInfo.setServerUrlAuth(array[5]);
        deviceInfo.setServerUrlSend(array[3]);
        String filePath = "E:\\University\\ThirdCourse\\APZ\\LabsAfterLight\\Task3\\apz-pzpi-21-5-ivakin-nikita-task3\\Iot\\src\\main\\java\\org\\example\\configFiles\\device-info.json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), deviceInfo);
            return "Data has been successfully written.";
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("An error occurred while writing to the JSON file. Please contact the tech support team.");
        }
    }
}
