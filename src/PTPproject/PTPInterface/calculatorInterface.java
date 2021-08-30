package PTPproject.PTPInterface;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Instituto Tecnológico de Costa Rica
 * Ingeniería en Computadores
 *
 * Clase: calculatorInterface
 * @version: 1.0
 * Lenguaje: Java
 *
 * @author: Byron Mata Fuentes
 *
 * Descripción: Esta clase crea, realiza el setup y le da el funcionamiento a la interfaz de la calculadora
 */
public class calculatorInterface extends JFrame { //Elementos swing de la interfaz
    private JPanel calcPanel;
    private JButton calcButton;
    private JTextField pvTextfield;
    private JTextField pcTextfield;
    private JTextField pwTextfield;
    private JLabel pvLabel;
    private JLabel paLabel;
    private JLabel pwLabel;
    private JLabel totalLabel;


    /**
     * Constructor que da Setup de la interfaz de la calculadora
     */
    public calculatorInterface() {
        setContentPane(calcPanel);
        setTitle("CalculatorApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setVisible(true);
        setResizable(false);



        calcButton.addActionListener(new ActionListener() { //Acción para escuchar del botón

            /**
             * Método que realiza las acciones de la calculadora al capturar los datos digitados
             * de los JTextField por medio del evento del botón
             */
            public void actionPerformed(ActionEvent e) {
                String pv = pvTextfield.getText(); //Declaración de variable del producto
                String pc = pcTextfield.getText(); //Declaración de variable del porcentaje
                String pw = pwTextfield.getText(); //Declaración de variable del peso

                double product = Double.valueOf(pv); //Conversión de dato de tipo String a tipo double
                double percentage = Double.valueOf(pc); //Conversión de dato de tipo String a tipo double
                double weight = Double.valueOf(pw); //Conversión de dato de tipo String a tipo double

                double total = (product * percentage / 100) + (weight * 0.15); //Cálculo

                System.out.println(total);
                String result = String.valueOf(total);
                totalLabel.setText(">> The total amount is: " + total);
            }
        });
    }

}
