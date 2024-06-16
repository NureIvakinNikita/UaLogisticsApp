import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.file.Paths;
import java.util.Scanner;

public class MulticastServerThread extends Thread {
    private static final String MULTICAST_GROUP = "230.0.0.0";
    private static final int PORT = 4446;

    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket();
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);

            Scanner scanner = new Scanner(System.in);
            int k = 0;
            while (k == 0) {
                System.out.print("Enter of the device and login ex (ID:12 LOGIN:scan.dev): ");
                String message = scanner.nextLine();
                message=message+"@gmail.com";



                if (message.split(" ").length < 2 || message.split(" ")[1].split(":").length < 2 || message.split(" ")[0].split(":").length < 2 ){
                    System.out.println("Wrong statement!!!");
                    continue;
                } else {
                    try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src", "url.txt").toString()))){
                        message = message + " URL(Send) "+reader.readLine()+ " URL(Auth) " + reader.readLine();
                    } catch (IOException e) {
                        System.out.println("File with url config doesn't exist update app!");
                    }
                    k = 1;
                }
                byte[] buffer = message.getBytes();

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
                socket.send(packet);
                System.out.println("Message sent: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        new MulticastServerThread().start();
    }
}