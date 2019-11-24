package code;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class Server extends JPanel {
    private JTextArea screen;

    Server() {
        JFrame frame = new JFrame();
        frame.setTitle("Server");
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#474747"));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        screen = new JTextArea();
        screen.setEditable(false);
        screen.setBackground(Color.decode("#28b85b"));
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setPreferredSize(new Dimension(360, 440));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }


    void handleAction(String s) {
        screen.append(s + '\n');
    }
}