import org.interpreter.SBasicInterpreter;
import org.interpreter.exceptions.InterpreterRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionsTest {
    private ByteArrayOutputStream buffer;

    @BeforeEach
    public void setUp() {
        buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
    }

    private String getAssertValue(String expected) {
        return expected.replaceAll("\r\n", System.lineSeparator());
    }

    @Test
    public void expressions_AdditionTwoNumbersAtVariable() {
        //Arrange
        var program = "A = 1 + 1; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("2.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_AdditionTwoNumbers() {
        //Arrange
        var program = "PRINT 1 + 1";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("2.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_ConcatTwoString() {
        //Arrange
        var program = "PRINT \"Hello \" + \"world!\"";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("Hello world!\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_ConcatTwoStringAtVariable() {
        //Arrange
        var program = "A = \"Hello \" + \"world!\"; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("Hello world!\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_ReassignVariable() {
        //Arrange
        var program = "A = 1; A = 2; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("2.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_AddTwoVariables() {
        //Arrange
        var program = "A = 1; B = 2; PRINT A + B";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("3.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_AddTwoVariablesInNewVariable() {
        //Arrange
        var program = "A = 1; B = 2; C = A + B; PRINT C";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("3.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_AddTwoVariablesStringInNewVariable() {
        //Arrange
        var program = "A = \"Hello \"; B = \"world!\"; C = A + B; PRINT C";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        String actual = buffer.toString();
        String expected = getAssertValue("Hello world!\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_SubtractionTwoNumbers() {
        //Arrange
        var program = "PRINT 10 - 5";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("5.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_SubtractionTwoVariables() {
        //Arrange
        var program = "A = 10; B = 5.5; PRINT A - B";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("4.5\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_SubtractionStrings() {
        //Arrange
        var program = "PRINT \"Hello\" - \"world!\"";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(InterpreterRuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Subtraction of non-numeric types is not supported.", exception.getMessage());
    }

    @Test
    public void expressions_MultiplyTwoNumbers() {
        //Arrange
        var program = "PRINT 2 * 3";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("6.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_MultiplyStrings() {
        //Arrange
        var program = "PRINT \"Hello\" * \"world!\"";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(InterpreterRuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Multiplication of non-numeric types is not supported.", exception.getMessage());
    }

    @Test
    public void expressions_DivideTwoNumbers() {
        //Arrange
        var program = "PRINT 10 / 2";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("5.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_DivideByZero() {
        //Arrange
        var program = "PRINT 10 / 0";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(InterpreterRuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Dividing by zero is not allowed.", exception.getMessage());
    }

    @Test
    public void expressions_DivideStrings() {
        var program = "PRINT \"Hello\" / \"world!\"";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(InterpreterRuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Dividing of non-numeric types is not supported.", exception.getMessage());
    }

    @Test
    public void expressions_ComplexArithmetic() {
        //Arrange
        var program = "PRINT (2 + 3) * 4";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("20.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_UnClosedBrackets() {
        //Arrange
        var program = "PRINT (2 + 3";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(RuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Missing closing parenthesis. At line 1", exception.getMessage());
    }

    @Test
    public void expressions_ComplexArithmeticWithVariables() {
        //Arrange
        var program = "A = 20; PRINT A - (2 + 3) * 4";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("0.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void expressions_ModulusTwoNumbers() {
        //Arrange
        var program = "PRINT 10 % 3";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("1.0\r\n");
        assertEquals(expected, actual);
    }
}
