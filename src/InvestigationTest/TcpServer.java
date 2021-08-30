//package InvestigationTest;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class TcpServer {
//
//    public static void main(String[] args) {
//
//        try
//        {
//            ServerSocket serverSocket = new ServerSocket(9999);
//            System.out.println("Waiting for connection...");
//            Socket socketS = serverSocket.accept();
//            Scanner scanner = new Scanner(System.in);
//            DataInputStream in = new DataInputStream(socketS.getInputStream());
//            DataOutputStream out = new DataOutputStream(socketS.getOutputStream());
//
//            while (true)
//            {
//                //Receiver
//                String msgC = in.readUTF();
//                System.out.println("Client >>: "+msgC);
//                if (msgC.equals("EXIT")) {
//                    System.out.println("The connection was disconnected");
//                    in.close();
//                    out.close();
//                    scanner.close();
//                    socketS.close();
//                    serverSocket.close();
//                }
//
//                //Send
//                System.out.println("Add msg: ");
//                String msgS = scanner.nextLine();
//                out.writeUTF(msgS);
//                if (msgS.equals("EXIT"))
//                {
//                    System.out.println("The connection was disconnected");
//                    in.close();
//                    out.close();
//                    scanner.close();
//                    socketS.close();
//                    serverSocket.close();
//                    System.exit(0);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
