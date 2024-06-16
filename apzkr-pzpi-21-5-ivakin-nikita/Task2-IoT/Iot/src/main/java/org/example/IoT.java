package org.example;

import org.example.service.IoTService;
import org.example.service.RequestService;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class IoT {

    protected String serverUrlSend = "";
    protected String serverUrlAuth = "";
    private static String token;
    protected int id = 0;
    protected String login = "";

    private static IoTService ioTService = new IoTService(new RequestService());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (ioTService.isDeviceConfigured()){
            System.out.println("Device is configured and ready for work.");
            System.out.print("Enter password for activation(If you don't know it write exit): ");
            while (true) {
                String password = scanner.nextLine();
                if (password.equals("exit")) {
                    System.out.println("Shutting down.");
                    return;
                }
                try {
                    token = ioTService.authenticate(password);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try again.");
                }
            }

            if (token.equals("")) {
                System.out.println("Device isn't authenticated try again");
                return;
            } else {
                System.out.println("Device is authenticated.");
                while (true) {
                    System.out.print("Waiting for Supply Car ID: ");
                    int carId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Scanning successful. Sending data to server...");
                    System.out.println(ioTService.sendScanData(carId));
                    System.out.println("If you want to turn the device down enter exit");
                    String exit = scanner.nextLine();
                    if (exit.equals("exit")) break;
                }
            }
        } else {
            System.out.println("Device isn't configured, call tech support team.");
        }
    }



    public void setServerUrlSend(String serverUrlSend) {
        this.serverUrlSend = serverUrlSend;
    }

    public void setServerUrlAuth(String serverUrlAuth) {
        this.serverUrlAuth = serverUrlAuth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getServerUrlSend() {
        return serverUrlSend;
    }

    public String getServerUrlAuth() {
        return serverUrlAuth;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
