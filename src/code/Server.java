package code;

import java.awt.BorderLayout;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setTitle("Server");

        JTextArea screen = new JTextArea();
        screen.setEditable(false);

        frame.add(screen, BorderLayout.CENTER);


        frame.setVisible(true);


        try {
            //creates registry at port 1099.
            Registry rmireg = LocateRegistry.createRegistry(1099);
            rmireg.rebind("Calculator", new RMI()); //binds the name "Calculator" with the remote object - rebinds if one exist already.
          //  System.out.println((String.valueOf(new Date()) + ": Server is Connected."));
			screen.append(new Date() + ": Server is Connected.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}