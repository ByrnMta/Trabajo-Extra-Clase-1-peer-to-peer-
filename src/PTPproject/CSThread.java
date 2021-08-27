package PTPproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CSThread extends Thread {

    private BufferedReader bufferedReader;
    public CSThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run(){
        boolean flag = true;

        while (flag) {
            try {
                System.out.println(bufferedReader.readLine());
            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
}
