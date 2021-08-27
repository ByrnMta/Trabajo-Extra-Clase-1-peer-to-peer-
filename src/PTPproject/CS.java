package PTPproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CS {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your username and port number: ");
        String[] setupValues = bufferedReader.readLine().split(" ");
        SCThread serverThread = new SCThread(setupValues[1]);
        serverThread.start();

        new CS().updateListenPeers(bufferedReader, setupValues[0], serverThread);
    }

    public void updateListenPeers(BufferedReader bufferedReader, String userName, SCThread serverThread) throws IOException {

        System.out.println(">> Enter hostname:port");
        System.out.println(">> Users to receive messages from... ");
        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");

        if (!input.equals("S")) for (int i = 0; i < inputValues.length; i++) {
            String[] address = inputValues[i].split(":");
            Socket socket = null;

            try {
                socket = new Socket(address[0], Integer.valueOf(address[1]));
                new CSThread(socket).start();
            } catch (Exception e) {

                if (socket != null) socket.close();
                else System.out.println(">> Invalid input...Skipping to next step");
            }
        }
        communicate(bufferedReader, userName, serverThread);
    }

    public void communicate(BufferedReader bufferedReader, String userName, SCThread serverThread) {
        
        try {
            System.out.println(">> You can communicate now...(C to change, E to exit & S to skip)");
            boolean flag = true;

            while (flag) {
                String message = bufferedReader.readLine();
                if (message.equals("E")) {
                    flag = false;
                    break;

                } else if (message.equals("C")) {
                    updateListenPeers(bufferedReader,userName, serverThread);

                } else {
                    String sms = ">"+userName+": "+message;
                    serverThread.sendMessage(sms);
                    System.out.println(sms);
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
