package org.interpreter;

import java.util.Scanner;

/**
 * The Main class serves as the entry point for the SBasic interpreter application.
 * It prompts the user to enter the path to a file containing SBasic source code,
 * initializes the interpreter, and executes the script.
 */
public class Main {

    /**
     * Default constructor for the Main class.
     * This constructor is implicitly called when the Main class is instantiated.
     */
    public Main() {
        // Default constructor
    }

    /**
     * The main method of the interpreter application.
     * It reads the path to the source code file from the user, initializes the interpreter,
     * and executes the script contained in the file.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to the file: ");
        String filePath = scanner.next();
        try {
            SBasicInterpreter interpreter = new SBasicInterpreter();
            interpreter.fromFile(filePath);
            interpreter.run();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
