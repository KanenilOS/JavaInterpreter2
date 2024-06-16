package org.interpreter.parser.expressions;

import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a numeric literal expression within the interpreted language.
 * This class encapsulates a double value, which is returned as is when the expression
 * is evaluated. It serves as the simplest form of expression, holding and returning
 * a constant numeric value.
 */
public class NumericExpression implements Expression {
    private final double value;

    /**
     * Constructs a new numeric expression with a specified double value.
     *
     * @param value the double value this expression will hold and return upon evaluation.
     */
    public NumericExpression(double value) {
        this.value = value;
    }

    /**
     * Evaluates this numeric expression and returns the encapsulated double value.
     * As this expression type holds a constant value, the evaluation simply returns
     * this value, unaffected by the runtime environment.
     *
     * @param env the runtime environment in which this expression is evaluated.
     *            This parameter is not used in this implementation because the
     *            numeric expression is independent of the environment.
     * @return the double value encapsulated by this numeric expression.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        return value;
    }
}
