package org.interpreter.parser.statements;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;
import java.util.Scanner;

/**
 * Represents an input statement in the interpreted language that prompts the user for input
 * and assigns the input to a specified variable within the runtime environment.
 * This statement is useful for interactive scripts or applications where user input is required.
 */
public class InputStatement implements Statement {
    private final String prompt;
    private final String variableName;

    /**
     * Constructs an InputStatement with a user prompt and the name of the variable where
     * the input will be stored.
     *
     * @param prompt the text displayed to the user as a prompt for input. Can be {@code null} if no prompt is needed.
     * @param variableName the name of the variable in the runtime environment that will store the user's input.
     */
    public InputStatement(String prompt, String variableName) {
        this.prompt = prompt;
        this.variableName = variableName;
    }

    /**
     * Executes this input statement by prompting the user for input and storing the input into a variable.
     * The input is expected to be numeric (double), and an exception is thrown if the input cannot be parsed to a double.
     *
     * @param env the runtime environment in which the variable is stored.
     * @throws InterpreterRuntimeException if the input is not a valid double.
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        // Optionally display a prompt if it's provided
        if (prompt != null) {
            System.out.print(prompt);
        }

        // Read the input from the user
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // Attempt to convert the user input into a double and store it in the specified variable
        try {
            double value = Double.parseDouble(input);
            env.setVariable(variableName, value);
        } catch (NumberFormatException e) {
            throw new InterpreterRuntimeException("Invalid input for a number: " + input);
        }
    }
}
