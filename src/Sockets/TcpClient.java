package Sockets;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TcpClient {

    public static void main(String[] args) {

        try
        {
            Socket clientSocket = new Socket("LocalHost", 9999);
            System.out.println("The connection is connected");
            Scanner scanner = new Scanner(System.in);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            while(true)
            {
                //Send
                System.out.println("Add msg: ");
                String msgC = scanner.nextLine();
                out.writeUTF(msgC);
                if (msgC.equals("EXIT"))
                {
                    System.out.println("The connection was disconnected");
                    in.close();
                    out.close();
                    scanner.close();
                    clientSocket.close();
                    System.exit(0);
                }

                //ZZzReceiver
                String msgS = in.readUTF();
                System.out.println("Client-2: "+msgS);
                if (msgS.equals("EXIT"))
                {
                    System.out.println("The connection was disconnected");
                    in.close();
                    out.close();
                    scanner.close();
                    clientSocket.close();
                    System.exit(0);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
