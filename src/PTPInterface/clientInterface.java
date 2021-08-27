package PTPInterface;

import PTPproject.CS;
import PTPproject.SCThread;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class clientInterface extends JFrame {
    private JPanel jPanel;
    private JPanel jSubpanel;
    private JTextField textField;
    private JButton sendButton;
    private JTextArea textArea;
    private JLabel jLabel;
    private JLabel jLabelL;

    public clientInterface() {
        setContentPane(jPanel);
        setTitle("BoxApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setResizable(false);

        sendButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    textArea.setText(">> Enter your username and port number: ");
                    //String portNumber = bufferedReader.readLine();
                    String portNumber = (textField.getText());
                    SCThread serverThread = new SCThread(portNumber);
                    serverThread.start();

                    new CS().listenUser(bufferedReader,portNumber, serverThread);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }


}
