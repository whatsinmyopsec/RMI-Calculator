package code;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Client class with calculator GUI.
 * This class handles the button actions of the calculator and sends the data to the RMI registry once submitted.
 * The RMI registry method will return back the result for it to be displayed on this GUI.
 *
 * @author Kevin Power - 20075681
 */

public class Client implements ActionListener, Calculator {

    private JTextField screen;
    private JTextArea resultsScreen;
    private JPanel panel, panelmdas, panelextras, reusltpanel;
    private String input;

    public static void main(String[] args) {
        new Client();
    }

    Client() {
        input = "";

        JFrame calcFrame = new JFrame();
        calcFrame.setTitle("Calculator - Client");
        calcFrame.setSize(400, 500);
        calcFrame.setBackground(Color.decode("#3c3c3c"));

        screen = new JTextField(26);
        screen.setBorder(new EmptyBorder(10, 10, 10, 10));
        screen.setEditable(false);
        screen.setBackground(Color.LIGHT_GRAY);
        screen.setHorizontalAlignment(JTextField.RIGHT);

        reusltpanel = new JPanel();
        reusltpanel.setBackground(Color.decode("#474747"));
        reusltpanel.setBorder(new EmptyBorder(10,10,10,10));

        resultsScreen = new JTextArea();
        resultsScreen.setEditable(false);
        resultsScreen.setBackground(Color.decode("#d4ffa3"));
        resultsScreen.setBorder(BorderFactory.createLineBorder(Color.black));
        resultsScreen.setPreferredSize(new Dimension(360,35));
        reusltpanel.add(resultsScreen);

        // loves the panels
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 1, 1));
        panel.setBorder(new EmptyBorder(10, 0, 10, 1));
        panel.setBackground(Color.decode("#474747"));

        panelmdas = new JPanel();
        panelmdas.setLayout(new GridLayout(4, 1));
        panelmdas.setBorder(new EmptyBorder(10, 10, 10, 1));
        panelmdas.setBackground(Color.decode("#474747"));

        panelextras = new JPanel();
        panelextras.setLayout(new GridLayout(3, 1));
        panelextras.setBorder(new EmptyBorder(10, 0, 10, 10));
        panelextras.setBackground(Color.decode("#474747"));

        //buttons in calculator
        String[] numbers = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
        String[] mdas = {"✖", "➗", "➖", "➕"};
        String[] extras = {"0", "="};


        //adding buttons for buttons array
        for (String i : numbers) {
            JButton button = new JButton(i);
            button.setPreferredSize(new Dimension(50, 50));
            button.setActionCommand(i);
            button.addActionListener(this);
            button.setForeground(Color.LIGHT_GRAY);
            button.setBorderPainted(false);
            button.setBackground(Color.decode("#060606"));
            panel.add(button);
        }

        for (String i : mdas) {
            JButton button = new JButton(i);
            button.setPreferredSize(new Dimension(50, 50));
            button.setActionCommand(i);
            button.addActionListener(this);
            button.setForeground(Color.LIGHT_GRAY);
            button.setBorderPainted(false);
            button.setBackground(Color.decode("#060606"));
            panelmdas.add(button);
        }

        for (String i : extras) {
            JButton btn = new JButton(i);
            btn.setPreferredSize(new Dimension(50, 50));
            btn.setActionCommand(i);
            btn.addActionListener(this);
            btn.setForeground(Color.LIGHT_GRAY);
            btn.setBorderPainted(false);
            btn.setBackground(Color.decode("#060606"));
            panelextras.add(btn);
        }


        //clear input
        JButton clear = new JButton("c");
        clear.setForeground(Color.LIGHT_GRAY);
        clear.setBorderPainted(false);
        clear.setBackground(Color.decode("#060606"));
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = screen.getText();
                answer = "";
                screen.setText(answer);
            }
        });
        panelextras.add(clear);

        //add all to frame
        calcFrame.add(panelmdas, BorderLayout.WEST);
        calcFrame.add(panel, BorderLayout.CENTER);
        calcFrame.add(panelextras, BorderLayout.EAST);
        calcFrame.add(screen, BorderLayout.PAGE_END);
        calcFrame.add(reusltpanel, BorderLayout.PAGE_START);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calcFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "0":
                input += "0";
                screen.setText(input);
                break;
            case "1":
                input += "1";
                screen.setText(input);
                break;
            case "2":
                input += "2";
                screen.setText(input);
                break;
            case "3":
                input += "3";
                screen.setText(input);
                break;
            case "4":
                input += "4";
                screen.setText(input);
                break;
            case "5":
                input += "5";
                screen.setText(input);
                break;
            case "6":
                input += "6";
                screen.setText(input);
                break;
            case "7":
                input += "7";
                screen.setText(input);
                break;
            case "8":
                input += "8";
                screen.setText(input);
                break;
            case "9":
                input += "9";
                screen.setText(input);
                break;
            case "➕":
                input += "➕";
                screen.setText(input);
                break;
            case "➖":
                input += "➖";
                screen.setText(input);
                break;
            case "➗":
                input += "➗";
                screen.setText(input);
                break;
            case "✖":
                input += "✖";
                screen.setText(input);
                break;
            case "c":
                input += "c";
                screen.setText(input);
                break;

            //when submitted
            case "=":
                try {
                    //creates gets the date and time when clicked.
                    String date = new Date() + ":  ";
                    //connects to localhost server (RMI registry)
                    Calculator calc = (Calculator) Naming.lookup("//localhost/Calculator");
                    if (calc.valid(input)) //checks if the input of the user is a valid equation to calculate.
                    {
                        //gets the operands of the input - allows +/- double operand values.
                        double a = calc.operand1(input);
                        double b = calc.operand2(input);
                        char operator = calc.operator(input); //gets the operator of the equation.
                        switch (operator) {
                            case '➕':
                                resultsScreen.append(date + input + " = " + calc.add(a, b) + "\n");
                                break;
                            case '➖':
                                resultsScreen.append(date + input + " = " + calc.subtract(a, b) + "\n");
                                break;
                            case '➗':
                                if (b == 0) {
                                    resultsScreen.append("You shall not pass");
                                    screen.setText("Cannot divide by Zero!");
                                }
                                resultsScreen.append(date + input + " = " + calc.divide(a, b) + "\n");
                                break;
                            default:
                                resultsScreen.append(date + input + " = " + calc.multiply(a, b) + "\n");
                                break;
                        }

                        //reset the input screen to blank.
                        screen.setText("");
                    } else //if the input is not a valid equation - it will print this.
                    {
                        resultsScreen.append(date + input + " is not valid! - Try again... \n");
                    }
                    //when calculation is complete or input is incorrect, set the input back to empty string.
                    input = "";
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return 0;
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        return 0;
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        return 0;
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        return 0;
    }

    @Override
    public boolean valid(String input) throws RemoteException {
        return false;
    }

    @Override
    public char operator(String input) throws RemoteException {
        return 0;
    }

    @Override
    public double operand1(String input) throws RemoteException {
        return 0;
    }

    @Override
    public double operand2(String input) throws RemoteException {
        return 0;
    }
}