package org.interpreter.parser.expressions;

import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents an equality expression in the language being parsed. This class
 * evaluates the equality of two expressions, checking whether their evaluated values
 * are equal to each other. It supports equality checks for all types that properly
 * implement the {@link Object#equals(Object)} method.
 */
public class EqualityExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs an equality expression with two sub-expressions.
     *
     * @param left the left-hand side expression to be evaluated and compared
     * @param right the right-hand side expression to be evaluated and compared
     */
    public EqualityExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the equality of the results of the left and right expressions.
     * This method first evaluates both the left and right expressions within the given
     * runtime environment, then compares their results using the {@link Object#equals(Object)}
     * method.
     *
     * @param env the runtime environment in which variable values and functions are defined
     * @return {@code true} if the evaluated results of both expressions are equal, {@code false} otherwise
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);
        return leftVal.equals(rightVal);
    }
}
