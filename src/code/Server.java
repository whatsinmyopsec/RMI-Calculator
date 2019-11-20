package code;

import java.awt.BorderLayout;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server {
    private JTextArea screen = new JTextArea();
        
    /*public static void main(String[] args) {
        new Server();
    }*/

    Server() {

        JFrame frame = new JFrame();
        frame.setTitle("Server");

        screen.setEditable(false);

        frame.add(screen, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }


    void handleAction(String s) {
        screen.append(s + '\n');
    }
}