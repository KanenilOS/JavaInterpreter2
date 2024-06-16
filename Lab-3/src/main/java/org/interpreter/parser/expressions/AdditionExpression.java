package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents an addition expression in the parsed language. This class handles
 * the addition of two expressions which can be either numerical or string types.
 * The addition follows basic rules: if both operands are numbers, their sum is
 * returned; if either operand is a string, string concatenation is performed.
 */
public class AdditionExpression implements Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Constructs a new addition expression with two operands.
     *
     * @param left the left operand as an Expression
     * @param right the right operand as an Expression
     */
    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Evaluates the addition expression within the provided runtime environment.
     * If both operands evaluate to Double, their sum is computed and returned.
     * If either operand is a String, concatenation is performed and the result is returned as a String.
     *
     * @param env the runtime environment containing variable values
     * @return the result of the addition or concatenation, depending on the operand types
     * @throws InterpreterRuntimeException if neither of the operands are Double or String
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) throws InterpreterRuntimeException {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        if (leftVal instanceof Double && rightVal instanceof Double) {
            return (Double) leftVal + (Double) rightVal;
        } else if (leftVal instanceof String || rightVal instanceof String) {
            return leftVal.toString() + rightVal.toString();
        } else {
            throw new InterpreterRuntimeException("Invalid operand types for addition. Both operands must be numbers or at least one must be a string.");
        }
    }
}
