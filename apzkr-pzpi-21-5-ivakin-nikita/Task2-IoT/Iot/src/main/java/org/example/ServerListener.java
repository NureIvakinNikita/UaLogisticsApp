package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.ServerListenerService;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.file.Paths;

public class ServerListener extends Thread{
    private static final String MULTICAST_GROUP = "230.0.0.0";
    private static final int PORT = 4446;

    private static ServerListenerService  serverListenerService = new ServerListenerService();

    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            socket.joinGroup(group);

            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String[] array = received.split(" ");
                try {
                    System.out.println(serverListenerService.saveData(array));
                    break;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

            }
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Something went wrong in configuring device, check app for updates and then try again.");;
        } finally {
            if (socket != null) {
                try {
                    socket.leaveGroup(InetAddress.getByName(MULTICAST_GROUP));
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ServerListener().start();
    }
}
