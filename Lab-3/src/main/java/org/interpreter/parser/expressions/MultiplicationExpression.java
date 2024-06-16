package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a multiplication expression in the interpreter.
 * This class is responsible for evaluating the multiplication of two numeric expressions.
 */
public class MultiplicationExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a MultiplicationExpression with the specified left and right expressions.
     *
     * @param left  the left operand of the multiplication.
     * @param right the right operand of the multiplication.
     */
    public MultiplicationExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the multiplication expression in the given runtime environment.
     *
     * @param env the runtime environment in which to evaluate the expression.
     * @return the result of multiplying the left and right expressions, if both are numeric.
     * @throws InterpreterRuntimeException if either operand is not a numeric value.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) throws InterpreterRuntimeException {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        if (leftVal instanceof Double && rightVal instanceof Double) {
            return (Double) leftVal * (Double) rightVal;
        } else {
            throw new InterpreterRuntimeException("Multiplication of non-numeric types is not supported.");
        }
    }
}
