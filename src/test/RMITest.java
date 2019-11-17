package test;

import static org.junit.jupiter.api.Assertions.*;

import java.rmi.RemoteException;

import code.RMI;
import org.junit.jupiter.api.Test;

class RMITest {

    String equation = "-78.23+392.23";
    double operand1 = -78.23;
    double operand2 = 392.23;
    RMI rmi;

    void setUp() throws RemoteException {
        rmi = new RMI();

    }

    @Test
    void addTest() throws RemoteException {
        setUp();
        assertEquals(314.0, rmi.add(operand1, operand2));
    }

    @Test
    void minusTest() throws RemoteException {
        setUp();
        assertEquals(-470.46, rmi.subtract(operand1, operand2));
    }

    @Test
    void multiplyTest() throws RemoteException {
        setUp();
        assertEquals(-30684.1529, rmi.multiply(operand1, operand2));
    }

    @Test
    void divideTest() throws RemoteException {
        setUp();
        assertEquals(-0.19944, rmi.divide(operand1, operand2));
    }

    @Test
    void validateTest() throws RemoteException {
        setUp();
        assertTrue(rmi.valid(equation));
    }

    @Test
    void operand1Test() throws RemoteException {
        setUp();
        assertEquals(-78.23, rmi.operand1(equation));
    }

    @Test
    void operand2Test() throws RemoteException {
        setUp();
        assertEquals(392.23, rmi.operand2(equation));
    }

    @Test
    void operatorTest() throws RemoteException {
        setUp();
        assertEquals('+', rmi.operator(equation));

        String test = "+312.23-+123.21";
        assertEquals('-', rmi.operator(test));

        String test2 = "-133.21/-123.32";
        assertEquals('/', rmi.operator(test2));

    }

}
