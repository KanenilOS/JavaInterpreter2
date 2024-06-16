package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a subtraction expression in the interpreted language.
 * This class handles the subtraction of two expressions, which must both evaluate to numeric (Double) values.
 * It ensures that both operands are numeric and performs the subtraction operation.
 */
public class SubtractionExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a new subtraction expression with specified left and right operands.
     *
     * @param left  the left operand expression, which serves as the minuend.
     * @param right the right operand expression, which serves as the subtrahend.
     */
    public SubtractionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the subtraction expression within the given runtime environment.
     * Both operands are evaluated to numeric values, and the subtraction is performed.
     *
     * @param env the runtime environment in which variable values and functions are defined.
     * @return the result of the subtraction as a Double.
     * @throws InterpreterRuntimeException if either operand is not a Double, indicating
     * that one or both operands are non-numeric, which is unsupported for subtraction.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        if (!(leftVal instanceof Double && rightVal instanceof Double)) {
            throw new InterpreterRuntimeException("Subtraction of non-numeric types is not supported.");
        }

        return (Double) leftVal - (Double) rightVal;
    }
}
