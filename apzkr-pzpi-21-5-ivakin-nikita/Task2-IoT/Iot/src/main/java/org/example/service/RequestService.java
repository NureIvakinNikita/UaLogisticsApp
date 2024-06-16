package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.RequestType;
import org.example.model.DeviceInfo;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RequestService {


    public String makePostRequest(RequestType requestType, String data, String token) throws Exception{
        String urlString = readUrl(requestType);
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        if (requestType.equals(RequestType.SEND_DATA) && token != null && !token.isEmpty()) {
            con.setRequestProperty("Authorization", "Bearer " + token);
        }
        con.setDoOutput(true);
        putDataToRequest(con, data);
        return getDataFromRequest(con);
    }

    public String getDataFromRequest(HttpURLConnection con) throws Exception{

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                response.append(responseLine.trim());
            }
            String responseString = response.toString();
            if (con.getResponseCode() == 200) return responseString;
            else throw new RuntimeException(response.toString());
        }
    }

    public String readUrl(RequestType requestType){
        String filePath = "E:\\University\\ThirdCourse\\APZ\\LabsAfterLight\\Task3\\apz-pzpi-21-5-ivakin-nikita-task3\\Iot\\src\\main\\java\\org\\example\\configFiles\\device-info.json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DeviceInfo authInfo = objectMapper.readValue(new File(filePath), DeviceInfo.class);
            if (requestType.equals(RequestType.AUTH)) {
                return authInfo.getServerUrlAuth();
            } else {
                return authInfo.getServerUrlSend();
            }
        } catch (IOException e) {
            return "Something went wrong in reading config file, call tech support team.";
        }

    }

    public void putDataToRequest(HttpURLConnection con ,String data) {
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = data.getBytes("utf-8");
            os.write(input, 0, input.length);
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong in putting data in request.");
        }
    }
}
