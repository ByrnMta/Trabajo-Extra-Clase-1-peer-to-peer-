package PTPproject.Sockets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: SC2Thread
 * @version: 1.0
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: Esta clase funciona como un depósito de hilos almacenados los cuales son preparados
 * para realizar la lectura y el transporte de los datos
 */
public class SC2Thread extends Thread {
    private SCThread serverThread; //Se declara la variable para el hilo del servidor
    private Socket socket; //Se declara la variable para el socket del cliente
    private PrintWriter printWriter; //Se declara la variable para PrintWriter


    /**
     * Constructor que crea a partir de los parámetros dados sockets de cliente e hilos de servidor
     * para los envíos y para la recepción
     *
     * @param socket variable de la cual se instancia el socket de algún cliente
     * @param serverThread variable para que se creen hilos de servidor
     */
    public SC2Thread(Socket socket, SCThread serverThread) {
        this.serverThread = serverThread;
        this.socket = socket;
    }


    /**
     * Método que mantiene en escucha a los hilos almacenados, a la vez les hace transportar y leer datos.
     */
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); //Lectura de datos del socket de cliente
            this.printWriter = new PrintWriter(socket.getOutputStream(), true); //Envío de datos del socket de cliente

            while (true) serverThread.sendMessage(bufferedReader.readLine());//Bucle para el envío o la lectura de datos

        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this); //Remueve uno de los hilos almacenados en caso de error
        }
    }


    /**
     * Método que devuelve la variable en el estado actual tras la conversión de los datos
     *
     * @return retorno de los datos enviados
     */
    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}
