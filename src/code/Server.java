package code;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class Server {
    private JTextArea screen = new JTextArea();

    Server() {
        JFrame frame = new JFrame();
        frame.setTitle("Server");
        screen.setEditable(false);
        frame.add(screen, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }


    void handleAction(String s) {
        screen.append(s + '\n');
    }
}