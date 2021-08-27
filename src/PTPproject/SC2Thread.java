package PTPproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SC2Thread extends Thread {
    private SCThread serverThread;
    private Socket socket;
    private PrintWriter printWriter;

    public SC2Thread(Socket socket, SCThread serverThread) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);

            while (true) serverThread.sendMessage(bufferedReader.readLine());

        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this);
        }
    }
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

}
