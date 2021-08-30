package PTPproject.PTPInterface;
import PTPproject.Sockets.CSThread;
import PTPproject.Sockets.SCThread;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: clientInterface
 * @version: 2.4
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: En esta clase se realiza el setup de la interfaz del cliente, en donde además se le da acción escucha
 * a los botones, los caules toman los datos del JTextField y realizan eventos como establecer nombre y puerto a un socket,
 * como crear la conexión entre sockets, también la posibilidad de enviar mensajes, cerrar la ejecución o bien hacer la
 * llamada del cálculo
 */
public class clientInterface extends JFrame { //Elementos swing de la interfaz
    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton startButton1;
    private JButton connectButton1;
    private JScrollPane scrollPanel1;
    private JLabel label1;
    private JTextArea textArea2;
    private JScrollPane scrollPanel2;


    /**
     * Setup de la interfaz del cliente
     */
    public clientInterface() {
        setContentPane(panel1);
        setTitle("BoxChat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setVisible(true);
        setResizable(false);

        textArea1.setLineWrap(true);
        textArea2.setLineWrap(true);
        textArea1.setText(">> To start enter your username and port number...\n>> Then press Start...");


        startButton1.addActionListener(new ActionListener() { //Acción para escuchar del botón

            /**
             * Método que toma datos de un JtextField y los guarda por separado en una variable la cual es dada a un
             * hilo de servidor y a la vez da inicio a dicho hilo, por medio del evento del botón
             *
             * @param e parámetro de evento
             */
            public void actionPerformed(ActionEvent e) {

                try {
                    if (textField1.getText().equals("")) {
                        textArea1.setText(">> Please enter your username and port number... \n>> Again press Start ...");

                    } else {
                        String[] userData = textField1.getText().split(" ");
                        SCThread serverThread = new SCThread(userData[1]);
                        serverThread.start();
                        textArea1.append("\n>> Enter the number of the port you want to connect...\n>> Then press Connect...");
                        System.out.println("Saved data");


                        sendButton.addActionListener(new ActionListener() { //Acción para escuchar del botón

                            /**
                             * Método que toma los datos del JTextField y los envía al otro socket de cliente, con el
                             * que establece conexión previamente, además este permite que por medio de una palabra el
                             * programa termine su ejecución y con ello corte la conexión, de igual manera sucede con
                             * la calculadora para la realización del cálculo, esto por medio del evento del botón
                             *
                             * @param e parámetro de evento
                             */
                            public void actionPerformed(ActionEvent e) {
                                String message = textField1.getText(); //Toma de los datos
                                String exit = ">> "+userData[0]+" has closed the port";
                                String sms = "> "+userData[0]+": "+message+"\n";
                                serverThread.sendMessage(sms); //Envío de datos
                                textArea2.append(sms);//Print de los datos en un JTextArea

                                if (message.equals("Ext")) { //Condición de terminación
                                    serverThread.sendMessage(exit);
                                    textArea1.append(exit);
                                    System.out.println("Disestablished connection...");
                                    System.exit(0);

                                } else if (message.equals("Calc")) { //Condición para realizar la instancia de la clase de la calculadora
                                    calculatorInterface calc = new calculatorInterface();
                                }}});

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }}});


        connectButton1.addActionListener(new ActionListener() { //Acción para escuchar del botón

            /**
             * Método que realiza la conexión de los puertos asignados a cada socket de cliente
             * esto por medio del evento del botón al tomar los datos del JTextField
             *
             * @param e parámetro de evento
             */
            public void actionPerformed(ActionEvent e) {
                String portNumber = textField1.getText();
                Socket socket = null; //Declaración de socket de cliente

                try {
                    socket = new Socket("localhost", Integer.valueOf(portNumber)); //Asignación del puerto ingresado
                    new CSThread(socket).start(); //Creación del socket
                    textArea1.append("\n>>Connection established, you can communicate...\n\n>>Information you should know... " +
                            "\n>> (Type Calc to request to perform the calculation)\n>>(Type Ext to exit)");
                    System.out.println("Established connection...");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    textArea1.append("\n>>The entered port is not open");
                }}});
    }
}