package code;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the RMI Registry that is used by the client to get results of inputs
 *
 * @author Kevin Power - 20075681
 */

public class RMI extends UnicastRemoteObject implements Calculator {

    private static Server view = new Server();
    private DecimalFormat decimal; //to round the double

    //Constructor
    public RMI() throws RemoteException {
        decimal = new DecimalFormat(".#####"); //round to 4 decimal points.
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Add Method Called : " + a + " ➕ " + b + "\nThe answer to this is : " + (a + b));
        return Double.parseDouble(decimal.format(a + b));
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Subtract Method Called : " + a + " ➖ " + b + "\nThe answer to this is : " + (a - b));
        return Double.parseDouble(decimal.format(a - b));
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        handleClientConnectionDetails();
        view.handleAction("Multiply Method Called : " + a + " ✖ " + b + "\nThe answer to this is : " + (a * b));
        return Double.parseDouble(decimal.format(a * b));
    }

    @Override
    public double divide(double a, double b) throws RemoteException {
        if (b == 0) {
            return 0;
        } else
            handleClientConnectionDetails();
        view.handleAction("Divide Method Called : " + a + " ➗ " + b + "\nThe answer to this is : " + (a / b));
        return Double.parseDouble(decimal.format(a / b));
    }

    /**
     * Checks if the string is valid
     *
     * @param input Equation
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean valid(String input) throws RemoteException {

        return Pattern.matches("^[\\-+]?\\d{1,}(\\.\\d{1,})?[-+✖÷][\\-+]?\\d{1,}(\\.\\d{1,})?$", input);
    }

    @Override
    public char operator(String input) throws RemoteException {
        //this specifies the regular expression to use to find the match
        Pattern operator = Pattern.compile("(?!^)[-+✖÷]"); //regular expression for finding the first operator that is not at the start of the string
        Matcher match = operator.matcher(input);
        if (match.find()) {
            return match.group(0).charAt(0); //return operator
        }
        return 0;

    }

    @Override
    public double operand1(String input) throws RemoteException {
        String nums = input.replaceFirst("(?!^)[-+✖÷]", "÷"); //replaces the operator into "/"
        //gets the first part of the string before the delimiter and converts to double
        return Double.parseDouble(nums.substring(0, nums.indexOf("÷")));

    }

    @Override
    public double operand2(String input) throws RemoteException {
        String nums = input.replaceFirst("(?!^)[-+✖÷]", "÷"); //replaces the operator into "/"
        //gets the first part of the string after the delimiter and converts to double
        return Double.parseDouble(nums.substring(nums.indexOf("÷") + 1));
    }

    private void handleClientConnectionDetails() {
        try {
            view.handleAction("Client Connected at : " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
    }

    /**
     * hack to get this thing to work
     *
     * @param args
     */

    public static void main(String... args) {
        try {
            RMI obj = new RMI();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Calculator", obj);
            view.handleAction("CalculatorServer bound in registry");
        } catch (Exception e) {
            System.out.println("CalculatorServer error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}