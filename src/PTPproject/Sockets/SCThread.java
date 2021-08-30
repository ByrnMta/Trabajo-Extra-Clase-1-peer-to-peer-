package PTPproject.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;


/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: SCThread
 * @version: 1.0
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: En esta clase se asignan los puertos para cada socket de cliente, además se instancia el socket del
 * servidor con el cual se acepta la conexión de estos, por otro lado, se crean los hilos y se da la conexión de los
 * mismos.
 */
public class SCThread extends Thread {
    private ServerSocket serverSocket; //Declaración de variable para ServerSocket
    private Set<SC2Thread> serverThreadThreads = new HashSet<SC2Thread>();


    /**
     * Constructor que le asigna el puerto a uno de los sockets creados anteriormente
     *
     * @param portNumber es quien guarda el número de puerto ingresado por uno de los clientes
     * @throws IOException es una manera de prevenir posibles fallos y avisar de ellos
     */
    public SCThread(String portNumber) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(portNumber)); //Se instancia el servidor con el puerto dado a la variable
    }


    /**
     * Método que le da inicio a la conexión de los sockets de cliente por medio del socket que funciona como servidor
     */
    public void run() {

        try {
            while (true) {
                SC2Thread serverThreadThread = new SC2Thread(serverSocket.accept(), this); //Se acepta la conexión
                serverThreadThreads.add(serverThreadThread); //Añade el hilo al depósito de hilos
                serverThreadThread.start(); //Da inicio a la conexión de los hilos
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Método que envía los datos digitados por medio de cada uno de los hilos
     *
     * @param message parámetro que guarda los datos digitados por alguno de los usuarios
     */
    public void sendMessage(String message) {
        try {
            serverThreadThreads.forEach(t-> t.getPrintWriter().println(message)); //Envía de los datos por cada uno de los hilos
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Método que obtiene los hilos que se encuentra almacenados en el depósito de hilos
     *
     * @return de los hilos almacenados
     */
    public Set<SC2Thread> getServerThreadThreads() {
        return serverThreadThreads;
    }

}
