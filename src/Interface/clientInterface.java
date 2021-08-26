package Interface;

import javax.swing.*;

public class clientInterface extends JFrame{
    private JPanel jPanel;
    private JPanel jSubpanel;
    private JTextField jTextfield;
    private JButton jButton;
    private JTextArea jTextarea;
    private JLabel jLabel;

    public clientInterface() {
        setContentPane(jPanel);
        setTitle("BoxApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setResizable(false);
    }
}
