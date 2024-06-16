package org.interpreter.parser.expressions;

import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a string literal expression within the interpreted language.
 * This class encapsulates a fixed string value, which is returned as is when the expression
 * is evaluated. It serves as the simplest form of expression for handling string data,
 * returning a constant value.
 */
public class StringExpression implements Expression {
    private final String value;

    /**
     * Constructs a new StringExpression with a specified string value.
     *
     * @param value the string value this expression will hold and return upon evaluation.
     */
    public StringExpression(String value) {
        this.value = value;
    }

    /**
     * Evaluates this string expression and returns the encapsulated string value.
     * As this expression type holds a constant value, the evaluation simply returns
     * this value, unaffected by the runtime environment.
     *
     * @param env the runtime environment in which this expression is evaluated.
     *            This parameter is not used in this implementation because the
     *            string expression is independent of the environment.
     * @return the string value encapsulated by this string expression.
     */
    @Override
    public Object evaluate(RuntimeEnvironment env) {
        return value;
    }
}
