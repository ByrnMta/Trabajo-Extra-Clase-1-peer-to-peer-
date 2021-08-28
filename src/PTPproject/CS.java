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

        System.out.println(">> Ingrese el número del puerto con el que desea comunicarse");
        String portNumber = bufferedReader.readLine();
        Socket socket = null;

            try {
                socket = new Socket("localhost", Integer.valueOf(portNumber));
                new CSThread(socket).start();
            } catch (Exception e) {

                if (socket != null) socket.close();
                else System.out.println(">> Entrada no válida...");
            }
        communication(bufferedReader, userName, serverThread);
    }

    public void communication(BufferedReader bufferedReader, String userName, SCThread serverThread) throws IOException {
        
        try {
            System.out.println(">> La conexión fue establecida...(Digite E para salir...)");
            boolean flag = true;
            while (flag) {
                String message = bufferedReader.readLine();
                String exit = new String(">> "+userName + " ha cerrado el puerto");
                Scanner scan = new Scanner(System.in);

                if (message.equals("E")) {
                    flag = false;
                    serverThread.sendMessage(exit);
                    break;

                } else if (message.equals("CALC")) {
                    System.out.println(">> Ingrese lo que se solicita a continuación...");
                    System.out.println(">> Valor del producto...");
                    double product = scan.nextDouble();
                    System.out.println(">> Porcentaje...");
                    double percentage = scan.nextDouble();
                    System.out.println(">> Peso del producto...");
                    double weight = scan.nextDouble();
                    double total = (product * percentage/100)+(weight*0.15);
                    System.out.println(total);

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

/*            String product;
            String percentage;
            String weight;
            String result = null;

            product = bufferedReader.readLine();
            percentage = bufferedReader.readLine();
            weight = bufferedReader.readLine();

            double v = Double.valueOf(product);
            double p = Double.valueOf(percentage);
            double w = Double.valueOf(weight);
            double r = Double.valueOf(result);

            r = (v*p/100)+(w*0.15);

            System.out.println(result);
            serverThread.sendMessage(String.valueOf(r));*/

    }
}

//Monto = (cost*percentage/100)+(weight*0,15)
