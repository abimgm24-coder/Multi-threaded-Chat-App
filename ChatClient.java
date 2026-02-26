import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9001);
        System.out.println("Connected to Chat Server! Type your message:");

        new Thread(() -> {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String serverResponse;
                while ((serverResponse = in.readLine()) != null) {
                    System.out.println("\n[Friend]: " + serverResponse);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            out.println(scanner.nextLine());
        }
    }
}

