package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a division expression in the parsed language. This class handles
 * the division of two expressions, both of which must evaluate to numeric (Double) types.
 * It includes handling for division by zero, which is a common runtime error in many programming languages.
 */
public class DivisionExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a new division expression with two operands.
     *
     * @param left the left operand as an Expression, which represents the dividend.
     * @param right the right operand as an Expression, which represents the divisor.
     */
    public DivisionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the division expression within the provided runtime environment.
     * Both operands are evaluated, and if both are numeric (Double), division is performed.
     *
     * @param env the runtime environment containing variable values
     * @return the result of the division as a Double
     * @throws InterpreterRuntimeException if either operand is not a Double or if division by zero occurs
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) throws InterpreterRuntimeException {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        if (!(leftVal instanceof Double && rightVal instanceof Double)) {
            throw new InterpreterRuntimeException("Dividing of non-numeric types is not supported.");
        }

        double divisor = (Double) rightVal;
        if (divisor == 0) {
            throw new InterpreterRuntimeException("Dividing by zero is not allowed.");
        }

        return (Double) leftVal / divisor;
    }
}
