package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.RequestType;
import org.example.model.AuthResponse;
import org.example.model.DeviceInfo;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class IoTService {

    private RequestService requestService;

    public String sendScanData(int carId){
        try {
            DeviceInfo deviceInfo = getDeviceInformation();
            String jsonInputString = new JSONObject()
                    .put("supplyCarId", carId)
                    .put("scanningDeviceId", deviceInfo.getId())
                    .toString();
            return requestService.makePostRequest(RequestType.SEND_DATA, jsonInputString, deviceInfo.getToken());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String authenticate(String password) {
        try {
            DeviceInfo deviceInfo = getDeviceInformation();
            String jsonInputString = new JSONObject()
                    .put("email", deviceInfo.getLogin())
                    .put("password", password)
                    .toString();
            String jsonResponse = requestService.makePostRequest(RequestType.AUTH, jsonInputString, "");
            if (!jsonResponse.isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                AuthResponse authResponse = objectMapper.readValue(jsonResponse, AuthResponse.class);
                saveToken(authResponse);
                return authResponse.getAccessToken();
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return "";

    }

    private void saveToken(AuthResponse authResponse) throws IOException{
        DeviceInfo deviceInfo = getDeviceInformation();
        deviceInfo.setToken(authResponse.getAccessToken());
        saveDeviceInfo(deviceInfo);
    }

    public DeviceInfo getDeviceInformation() throws IOException {

        String filePath = "E:\\University\\ThirdCourse\\APZ\\LabsAfterLight\\Task3\\apz-pzpi-21-5-ivakin-nikita-task3\\Iot\\src\\main\\java\\org\\example\\configFiles\\device-info.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DeviceInfo authInfo = objectMapper.readValue(new File(filePath), DeviceInfo.class);
            return authInfo;
        } catch (IOException e) {
            throw new IOException("File with device data not found, call support team.");
        }

    }

    public boolean isDeviceConfigured() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src", "config.txt").toString()))){
            reader.readLine();
            reader.readLine();
            int id = 0;
            id = Integer.parseInt(reader.readLine());
            String email = "";
            email = reader.readLine();
            if (id !=0 && !email.equals("")) {
                return true;
            }
            else return false;
        } catch (IOException e) {
            System.out.println("File with url config doesn't exist update IoT! For this call tech support.");
            return false;
        }
    }


    public String saveDeviceInfo(DeviceInfo deviceInfo){
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

    public IoTService(RequestService requestService) {
        this.requestService = requestService;
    }
}
