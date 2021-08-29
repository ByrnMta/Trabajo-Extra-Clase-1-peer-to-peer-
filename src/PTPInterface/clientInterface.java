package PTPInterface;

import PTPproject.CSThread;
import PTPproject.SCThread;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class clientInterface extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextArea textArea1;
    private JButton startButton1;
    private JButton connectButton1;
    private JScrollPane scrollPanel1;
    private JLabel label1;

    public clientInterface() {
        setContentPane(panel1);
        setTitle("BoxApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        setVisible(true);
        setResizable(false);
        textArea1.setLineWrap(true);
        textArea1.setText(">> To start enter your username and port number... Then press Start...");

        startButton1.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                try {
                    if (textField1.getText().equals("")) {
                        textArea1.setText(">> Please enter your username and port number... Again press Start ...");

                    } else {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        String[] userData = textField1.getText().split(" ");
                        SCThread serverThread = new SCThread(userData[1]);
                        serverThread.start();
                        textArea1.append("\n>> Now enter the number of the port you want to connect to... Then press Connect...");
                        System.out.println("Saved data");

                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                sendButton.addActionListener(new ActionListener() {
                    @Override

                    public void actionPerformed(ActionEvent e) {

                        boolean flag = true;

                        while (flag) {
                            String message = textField1.getText();
                            System.out.println(message);
                            //String ext = new String(">> A user has dropped the connection.");

                            if (message.equals("Ext")) {
                                flag = false;
                                textArea1.append("\n>> A user has dropped the connection.");
                                System.out.println("Disestablished connection...");
                                //serverThread.sendMessage(ext);

                            } else if (message.equals("Calc")) {
                                textArea1.append("\n>> To perform the calculation enter what is requested below...");
                                textArea1.append("\n>> Product value...");
                                String product = textField1.getText();
                                textArea1.append("\n>> Percentage to apply...");
                                String percentage = textField1.getText();
                                textArea1.append("\n>> Product weight...");
                                String weight = textField1.getText();

                                double p = Double.valueOf(product);
                                double pg = Double.valueOf(percentage);
                                double w = Double.valueOf(weight);
                                double total = (p * pg/100)+(w*0.15);

                                textArea1.append("\n>> The total amount is: "+total);
                                //serverThread.sendMessage(String.valueOf(total));

                            } else {
                                //String sms = new String("> "+message);
                                //serverThread.sendMessage(sms)
                                textArea1.append("> "+message);
                            }
                        }
                    }
                });



            }
        });

        connectButton1.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                String portNumber = textField1.getText();
                Socket socket = null;

                try {
                    socket = new Socket("localhost", Integer.valueOf(portNumber));
                    new CSThread(socket).start();
                    textArea1.append("\n>>Connection established, now you can communicate...\n>>Information you should know... " +
                            "(Type Calc to request to perform the calculation)|(Type Ext to exit)");
                    System.out.println("Established connection...");

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //communication(bufferedReader, userName, serverThread);
            }
        });
    }
}
