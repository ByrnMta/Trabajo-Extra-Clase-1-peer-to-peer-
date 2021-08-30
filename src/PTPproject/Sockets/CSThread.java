package PTPproject.Sockets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: CSThread
 * @version: 1.0
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: Esta clase permite que uno de los sockets de cliente reciba y realice la lectura de los datos que son
 * enviados desde otro socket de cliente por medio de hilos
 */
public class CSThread extends Thread {
    private BufferedReader bufferedReader; //Declaración de variable para BufferReader


    /**
     * Constructor que permite la lectura de los datos enviados por un socket de cliente
     * @param socket crea el sistema de comunicación
     * @throws IOException es una manera de prevenir posibles fallos y avisar de ellos
     */
    public CSThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); }


    /**
     * Método que permite que los datos enviados se transporten por medio de un hilo
     */
    public void run(){
        boolean flag = true;
        while (flag) {
            try {
                System.out.println(bufferedReader.readLine()); //Print en la terminal de los datos enviados
            } catch (Exception e) {
                flag = false;
                interrupt(); //Interrumpe el traspaso de los datos en caso de error
            }
        }
    }
}
