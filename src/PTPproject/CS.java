package PTPproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class CS {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(">> Ingrese el su nombre de usuario y su número de puerto...");
        String[] setupValues = bufferedReader.readLine().split(" ");
        SCThread serverThread = new SCThread(setupValues[1]);
        serverThread.start();

        new CS().listenUser(bufferedReader, setupValues[0], serverThread);
    }

    public void listenUser(BufferedReader bufferedReader, String userName, SCThread serverThread) throws IOException {
        System.out.println(">> Ingrese el número de puerto con el que se desea comunicar...");
        String portNumber = bufferedReader.readLine();
        Socket socket = null;

            try {
                socket = new Socket("localhost", Integer.valueOf(portNumber));
                new CSThread(socket).start();
            } catch (Exception e) {

                if (socket != null) socket.close();
                else System.out.println(">> Entrada no válida... Intente de nuevo");
            }
        communication(bufferedReader, userName, serverThread);
    }

    public void communication(BufferedReader bufferedReader, String userName, SCThread serverThread) throws IOException {
        
        try {
            System.out.println(">> La conexión fue establecida...(Digite Inf para solicitar información...)");
            boolean flag = true;

            while (flag) {
                Scanner scan = new Scanner(System.in);
                String message = bufferedReader.readLine();
                String exit = new String(">> "+userName + " ha cerrado el puerto");

                if (message.equals("Ext")) {
                    flag = false;
                    serverThread.sendMessage(exit);
                    break;

                } else if (message.equals("Inf")){
                    System.out.println("**Digite Ext para salir**");
                    System.out.println("**Digite Calc para solicitar el cáculo**");

                }else if (message.equals("Calc")) {
                    System.out.println(">> Para realizar el cálculo ingrese lo que se solicita a continuación...");
                    System.out.println(">> Valor del producto...");
                    double product = scan.nextDouble();
                    System.out.println(">> Porcentajea aplicar...");
                    double percentage = scan.nextDouble();
                    System.out.println(">> Peso del producto...");
                    double weight = scan.nextDouble();
                    double total = (product * percentage/100)+(weight*0.15);
                    System.out.println("El monto total es de: "+total);
                    String mont = new String("El monto total solicitado"+userName+"es de: "+total);
                    //serverThread.sendMessage(String.valueOf(mont));

                } else {
                    String sms = ">"+userName+": "+message;
                    serverThread.sendMessage(sms);
//                    System.out.println(sms);
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


