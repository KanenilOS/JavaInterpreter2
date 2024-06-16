package org.interpreter.parser.statements;

import org.interpreter.parser.expressions.Expression;
import org.interpreter.runtime.RuntimeEnvironment;
import java.util.List;

/**
 * Represents a print statement in the interpreted language. This statement is used to output
 * a sequence of expressions and strings to the console. Each element in the provided list
 * is evaluated if it is an expression or directly appended if it is a string.
 */
public class PrintStatement implements Statement {
    private final List<Object> elements;

    /**
     * Constructs a PrintStatement with a list of elements to be printed. Each element
     * in the list can either be a literal string or an expression that needs to be evaluated.
     *
     * @param elements a list of elements ({@link Object}), where each element can be either
     *                 a {@link String} or an {@link Expression}.
     */
    public PrintStatement(List<Object> elements) {
        this.elements = elements;
    }

    /**
     * Executes the print statement by evaluating any expressions and concatenating their results
     * with any strings, then printing the final concatenated result to the console. This method
     * handles both the evaluation of expressions and the direct printing of string literals.
     *
     * @param env the runtime environment where expressions are evaluated. It provides the necessary
     *            context for variable values and other runtime data needed by expressions.
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        StringBuilder output = new StringBuilder();
        for (Object element : elements) {
            if (element instanceof String) {
                output.append(element);
            } else if (element instanceof Expression) {
                Object result = ((Expression) element).evaluate(env);
                output.append(result);
            }
            output.append(" ");  // Add a space between elements for better readability
        }
        // Print the final output string, trimming to remove the last space
        System.out.println(output.toString().trim());
    }
}
