package org.interpreter;

import org.interpreter.lexer.BasicLexer;
import org.interpreter.lexer.Lexer;
import org.interpreter.parser.Parser;
import org.interpreter.parser.statements.Statement;
import org.interpreter.runtime.RuntimeEnvironment;
import org.interpreter.token.Token;
import org.interpreter.token.TokenType;

import java.io.*;
import java.util.List;

/**
 * The SBasicInterpreter class is the main entry point for the SBasic interpreter.
 * It is responsible for setting up the lexer, parser, and runtime environment,
 * and for executing the parsed statements.
 */
public class SBasicInterpreter {
    private Parser parser;
    private RuntimeEnvironment environment;

    /**
     * Default constructor. Creates an uninitialized interpreter.
     * Use the fromFile method or the parameterized constructor to initialize the interpreter.
     */
    public SBasicInterpreter() {}

    /**
     * Constructs an interpreter with the provided input source code.
     * Initializes the lexer, parser, and runtime environment.
     *
     * @param input the source code to be interpreted.
     */
    public SBasicInterpreter(String input) {
        Lexer lexer = new BasicLexer(input);
        this.environment = new RuntimeEnvironment();
        this.parser = new Parser(lexer, environment);
    }

    /**
     * Reads the source code from a file and initializes the lexer, parser, and runtime environment.
     *
     * @param path the path to the source code file.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    public void fromFile(String path) throws IOException {
        String input = readFile(path);
        Lexer lexer = new BasicLexer(input);
        this.environment = new RuntimeEnvironment();
        this.parser = new Parser(lexer, environment);
    }

    /**
     * Reads the entire content of a file into a string.
     *
     * @param filePath the path to the file to be read.
     * @return the content of the file as a string.
     * @throws IOException if an I/O error occurs reading from the file.
     */
    private String readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        FileReader fileReader = new FileReader(filePath);
        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            throw e;
        }
        return contentBuilder.toString();
    }

    /**
     * Executes the parsed statements using the runtime environment.
     * Ensures that the parser and runtime environment are initialized before execution.
     */
    public void run() {
        if(parser == null || environment == null) {
            System.out.println("Parser or RuntimeEnvironment is not initialized! Use fromFile method or constructor instead!");
            return;
        }

        List<Statement> statements = parser.parse();
        for (Statement statement : statements) {
            statement.execute(environment);
        }
    }
}

