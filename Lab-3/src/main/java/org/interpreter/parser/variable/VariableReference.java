package org.interpreter.parser.variable;

import org.interpreter.parser.expressions.Expression;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a reference to a variable within the interpreted language. This class
 * provides the functionality to retrieve the value of a variable from the runtime
 * environment during the evaluation of expressions.
 */
public class VariableReference implements Expression {
    private final String variableName;

    /**
     * Constructs a VariableReference for a specific variable.
     *
     * @param variableName the name of the variable this reference points to. This name
     *                     is used to retrieve the variable's value from the runtime environment
     *                     during expression evaluation.
     */
    public VariableReference(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Evaluates this variable reference within the context of the specified runtime environment.
     * It retrieves and returns the value of the variable identified by {@code variableName}.
     *
     * @param env the runtime environment from which the variable's value is fetched.
     * @return the value of the variable, which can be any Object depending on the variable's type
     *         and current value.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        return env.getVariable(variableName);
    }

    /**
     * Returns a string representation of the variable reference, which is simply the name of the variable.
     * This can be useful for debugging purposes and when generating error messages or logs.
     *
     * @return the name of the referenced variable.
     */
    @Override
    public String toString() {
        return variableName;
    }
}
