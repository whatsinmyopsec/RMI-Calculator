package code;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is accessed by the client via the RMI registry.
 * It specifies all the methods required for this calculator app.
 *
 * @author Kevin Power - 20075681
 */

public interface Calculator extends Remote {

    /**
     * Adds two double values together and returns the sum.
     *
     * @param a
     * @param b
     * @return double
     * @throws RemoteException
     */
    double add(double a, double b) throws RemoteException;

    /**
     * Subtracts two double values and returns a double answer.
     *
     * @param a
     * @param b
     * @return double
     * @throws RemoteException
     */
    double subtract(double a, double b) throws RemoteException;

    /**
     * Multiply two double values together and returns a double answer.
     *
     * @param a
     * @param b
     * @return double
     * @throws RemoteException
     */
    double multiply(double a, double b) throws RemoteException;

    /**
     * Divides two double values and returns a double answer.
     *
     * @param a
     * @param b
     * @return double
     * @throws RemoteException
     */
    double divide(double a, double b) throws RemoteException;

    /**
     * Takes in a string equation and validates if it matches the format specified.
     *
     * @param input Equation
     * @return boolean
     * @throws RemoteException
     */
    boolean valid(String input) throws RemoteException;

    /**
     * Takes in a string equation and gets the operator that's used.
     *
     * @param input Equation
     * @return char operator
     * @throws RemoteException
     */
    char operator(String input) throws RemoteException;

    /**
     * Takes in a string equation and returns the first operand of the equation. i.e. the double value before the operator.
     *
     * @param input
     * @return double operand 1
     * @throws RemoteException
     */
    double operand1(String input) throws RemoteException;

    /**
     * Takes in a string equation and returns the second operand of the equation. i.e. the double value after the operator.
     *
     * @param input
     * @return double operand 2
     * @throws RemoteException
     */
    double operand2(String input) throws RemoteException;

}