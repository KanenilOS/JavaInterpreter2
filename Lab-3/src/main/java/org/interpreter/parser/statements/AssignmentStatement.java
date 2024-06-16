package org.interpreter.parser.statements;

import org.interpreter.parser.expressions.Expression;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents an assignment statement in the interpreted language. This statement
 * assigns the result of an expression to a specified variable within the runtime environment.
 * The assignment modifies the current state of the environment by updating the value
 * of the variable.
 */
public class AssignmentStatement implements Statement {
    private final String variableName;
    private final Expression expression;

    /**
     * Constructs an AssignmentStatement with a specific variable name and an associated expression.
     * The value evaluated from the expression will be assigned to the variable in the runtime environment.
     *
     * @param variableName the name of the variable that will receive the value of the evaluated expression
     * @param expression the expression to evaluate and assign to the variable
     */
    public AssignmentStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    /**
     * Executes the assignment operation within the given runtime environment.
     * Evaluates the expression and assigns its result to the variable specified by {@code variableName}.
     * This method alters the state of the runtime environment by setting the variable's value.
     *
     * @param env the runtime environment in which the variable assignment is performed
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        Object value = expression.evaluate(env);
        env.setVariable(variableName, value);
    }
}
