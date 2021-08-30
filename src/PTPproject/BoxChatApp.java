package PTPproject;


import PTPproject.PTPInterface.clientInterface;

/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: BoxApp
 * @version: 1.0
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: Esta clase contiene el método principal y ejecutable del programa
 */
public class BoxChatApp {


    /**
     * Método main del proyecto
     * Método que permite la ejecución del programa
     *
     * @param args es un arreglo para con los parámetros que se reciban por consola o terminal
     */
    public static void main(String[] args) {
        clientInterface runnable = new clientInterface();
    }
}
