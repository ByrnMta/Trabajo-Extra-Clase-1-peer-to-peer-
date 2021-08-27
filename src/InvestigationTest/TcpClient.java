package InvestigationTest;

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

                //Receiver
                String msgS = in.readUTF();
                System.out.println("Client >>: "+msgS);
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
