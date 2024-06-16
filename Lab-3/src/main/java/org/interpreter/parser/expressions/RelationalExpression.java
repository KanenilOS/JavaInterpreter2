package org.interpreter.parser.expressions;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.runtime.RuntimeEnvironment;
import org.interpreter.token.TokenType;

/**
 * Represents a relational expression in the interpreted language.
 * This class evaluates relational comparisons (such as less than, greater than,
 * equal to, etc.) between two sub-expressions. It supports both numeric comparisons
 * and equality checks for any type of operands that implement the {@link Object#equals(Object)} method.
 */
public class RelationalExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final TokenType operator;

    /**
     * Constructs a relational expression with specified left and right operands
     * and a relational operator.
     *
     * @param left     the left-hand side expression to be evaluated and compared.
     * @param operator the relational operator defining the type of comparison ({@link TokenType}).
     * @param right    the right-hand side expression to be evaluated and compared.
     */
    public RelationalExpression(Expression left, TokenType operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    /**
     * Evaluates the relational expression within the given runtime environment.
     * Depending on the operator, this method performs various comparisons:
     * numeric comparisons for relational operators and equality checks for equality operators.
     *
     * @param env the runtime environment in which variable values and functions are defined.
     * @return the result of the comparison as a Boolean, or throws an exception if the operation is not supported or operands are not of required types.
     * @throws InterpreterRuntimeException if the operands are not suitable for the specified operator (e.g., non-numeric types for numeric comparisons).
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        Object leftVal = left.evaluate(env);
        Object rightVal = right.evaluate(env);

        return switch (operator) {
            case LESS_THAN -> checkDouble(leftVal, rightVal) && (Double) leftVal < (Double) rightVal;
            case GREATER_THAN -> checkDouble(leftVal, rightVal) && (Double) leftVal > (Double) rightVal;
            case LESS_EQUAL -> checkDouble(leftVal, rightVal) && (Double) leftVal <= (Double) rightVal;
            case GREATER_EQUAL -> checkDouble(leftVal, rightVal) && (Double) leftVal >= (Double) rightVal;
            case EQUAL -> leftVal.equals(rightVal);
            case NOT_EQUAL -> !leftVal.equals(rightVal);
            default -> throw new InterpreterRuntimeException("Unsupported relational operation");
        };
    }

    /**
     * Checks if both provided values are instances of {@link Double}.
     * This method is used to ensure that the operands are suitable for numeric comparisons.
     *
     * @param leftVal  the value of the left operand.
     * @param rightVal the value of the right operand.
     * @return true if both operands are doubles.
     * @throws InterpreterRuntimeException if either operand is not a double.
     */
    private boolean checkDouble(Object leftVal, Object rightVal) {
        if (!(leftVal instanceof Double && rightVal instanceof Double)) {
            throw new InterpreterRuntimeException("Relational operations require numeric operands.");
        }
        return true;
    }
}
