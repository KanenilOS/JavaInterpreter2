package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a modulus expression in the language being interpreted. This class
 * evaluates the modulus (remainder) of dividing the left expression by the right expression.
 * It ensures that both operands are numeric and handles cases where the divisor is zero.
 */
public class ModulusExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a new modulus expression with specified left and right operands.
     *
     * @param left  the left operand expression, which serves as the dividend.
     * @param right the right operand expression, which serves as the divisor.
     */
    public ModulusExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the modulus expression within the given runtime environment. Both operands
     * are evaluated to numeric values, and the modulus operation is performed.
     *
     * @param env the runtime environment in which variable values and functions are defined.
     * @return the result of the modulus operation as a Double.
     * @throws InterpreterRuntimeException if the right operand evaluates to zero, as modulus by zero is undefined.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        double leftVal = ensureNumeric(left.evaluate(env));
        double rightVal = ensureNumeric(right.evaluate(env));

        if (rightVal == 0) {
            throw new InterpreterRuntimeException("Modulus by zero is undefined.");
        }

        return leftVal % rightVal;
    }

    /**
     * Ensures that the given object is a numeric value, casting it to Double if it is.
     * If the object is not a Double, an InterpreterRuntimeException is thrown.
     *
     * @param value the object to check and cast.
     * @return the numeric value as a Double.
     * @throws InterpreterRuntimeException if the object is not a numeric value.
     */
    private double ensureNumeric(Object value) {
        if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new InterpreterRuntimeException("Expected numeric value for modulus operation.");
        }
    }
}
