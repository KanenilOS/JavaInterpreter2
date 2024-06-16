import org.interpreter.SBasicInterpreter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StatementsTest {
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
    public void statements_BasicPrint() {
        //Arrange
        var program = "PRINT \"Hello world!\"";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("Hello world!\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_NumberVariableAssigning() {
        //Arrange
        var program = "A = 10; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("10.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_FloatVariableAssigning() {
        //Arrange
        var program = "A = 5.5; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("5.5\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_StringVariableAssigning() {
        //Arrange
        var program = "A = \"Test variable\"; PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("Test variable\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_UninitializedVariable() {
        //Arrange
        var program = "PRINT A";
        var interpreter = new SBasicInterpreter(program);

        //Act
        Throwable exception = assertThrows(RuntimeException.class, interpreter::run);

        //Assert
        assertEquals("Runtime error: Variable 'A' not initialized", exception.getMessage());
    }

    @Test
    public void statements_IfStatementEqual() {
        //Arrange
        var program = "A = 10; IF A % 2 = 0 THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatementNotEqual() {
        //Arrange
        var program = "A = 10; IF A % 2 <> 1 THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatementLess() {
        //Arrange
        var program = "A = 10; IF 1 < A THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatementGreater() {
        //Arrange
        var program = "A = 10; IF A > 1 THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatementGreaterEqual() {
        //Arrange
        var program = "A = 10; IF A >= 10 THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatementLessEqual() {
        //Arrange
        var program = "A = 10; IF A <= 10 THEN PRINT \"True\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("True\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfElseStatement() {
        //Arrange
        var program = "IF 10 < 2 THEN PRINT \"True\" ELSE PRINT \"False\" ENDIF";
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("False\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_IfStatement() {
        //Arrange
        var program = """
                A = 10
                B = 20
                IF A = B THEN PRINT "A = B" ENDIF
                IF A <> B THEN PRINT "A <> B" ENDIF
                IF A < B THEN PRINT "A < B" ENDIF
                IF A > B THEN PRINT "A > B" ENDIF
                IF A >= B THEN PRINT "A >= B" ENDIF
                IF A <= B THEN PRINT "A <= B" ENDIF
                """;
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                A <> B\r
                A < B\r
                A <= B\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_ForLoop() {
        //Arrange
        var program = """
                FOR X = 1 TO 5
                    PRINT X
                NEXT
                """;
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                1.0\r
                2.0\r
                3.0\r
                4.0\r
                5.0\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_PrintMultipleExpressions() {
        //Arrange
        var program = """
                X = 10
                PRINT X; X/2, X; X*X
                """;
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = getAssertValue("10.0 5.0 10.0 100.0\r\n");
        assertEquals(expected, actual);
    }

    @Test
    public void statements_Input() {
        //Arrange
        var program = """          
                INPUT "Enter length ", l      
                PRINT "Length", l
                """;
        var interpreter = new SBasicInterpreter(program);
        System.setIn(new ByteArrayInputStream("7".getBytes()));

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                Enter length Length 7.0\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_NestedForLoops() {
        //Arrange
        var program = """
                PRINT "This program demonstrates nested FOR loops."
                FOR X = 1 TO 2
                    FOR Y = 1 TO 3
                        PRINT X; Y; X*Y
                    NEXT
                NEXT
                """;
        var interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                This program demonstrates nested FOR loops.\r
                1.0 1.0 1.0\r
                1.0 2.0 2.0\r
                1.0 3.0 3.0\r
                2.0 1.0 2.0\r
                2.0 2.0 4.0\r
                2.0 3.0 6.0\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_GoTo() {
        //Arrange
        var program = """
        PRINT "Start"
        GOTO Skip
        PRINT "This should not print"
        Skip:
        PRINT "This should print"
        """;
        SBasicInterpreter interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                Start\r
                This should print\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_GoSub() {
        //Arrange
        var program = """
        PRINT "Start"
        GOSUB Skip
        PRINT "This should print also"
        Skip:
        PRINT "This should print"
        RETURN
        """;
        SBasicInterpreter interpreter = new SBasicInterpreter(program);

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                Start\r
                This should print\r
                This should print also\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }

    @Test
    public void statements_NestedGoSub() {
        //Arrange
        var program = """
            PRINT "This program demonstrates nested GOSUBs."
            INPUT "Enter a number: ", I
            GOSUB LOOP
            
            LOOP:
            X = 0
            FOR T = 1 TO I  
                X = X + I
                GOSUB RESULT            
            NEXT              
            RETURN
            
            RESULT: 
                PRINT X;
            RETURN
            """;
        SBasicInterpreter interpreter = new SBasicInterpreter(program);
        System.setIn(new ByteArrayInputStream("3".getBytes()));

        //Act
        interpreter.run();

        //Assert
        String actual = buffer.toString();
        String expected = """
                This program demonstrates nested GOSUBs.\r
                Enter a number: 3.0\r
                6.0\r
                9.0\r
                """;
        assertEquals(getAssertValue(expected), actual);
    }
}
