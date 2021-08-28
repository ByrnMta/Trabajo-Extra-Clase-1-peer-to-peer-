package PTPproject;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class SCThread extends Thread {
    private ServerSocket serverSocket;
    private Set<SC2Thread> serverThreadThreads = new HashSet<SC2Thread>();
    public SCThread(String portNumber) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(portNumber));
    }

    @Override
    public void run() {

        try {
            while (true) {
                SC2Thread serverThreadThread = new SC2Thread(serverSocket.accept(), this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String message) {
        try {
            serverThreadThreads.forEach(t-> t.getPrintWriter().println(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<SC2Thread> getServerThreadThreads() {
        return serverThreadThreads;
    }

//    public void sendMessage(double total) {} CAMBIO DE METODO DE CALCULO
}
