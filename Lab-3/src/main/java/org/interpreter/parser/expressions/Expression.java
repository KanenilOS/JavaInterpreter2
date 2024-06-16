package org.interpreter.parser.expressions;

import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a generic expression in the programming language being interpreted.
 * This interface defines the essential framework for all expression types, ensuring
 * that they can be evaluated within a given runtime environment. Implementations of
 * this interface are responsible for defining the specific evaluation behavior
 * for different kinds of expressions.
 */
public interface Expression {

    /**
     * Evaluates this expression within the context of the specified runtime environment.
     * The method returns the result of the expression, which can vary in type depending on
     * the specific expression being evaluated. For example, numeric calculations might return
     * {@link Double}, logical operations might return {@link Boolean}, and others might return
     * {@link String} or complex data structures.
     *
     * @param env the runtime environment providing context for variable values, functions, and other
     *            runtime configurations necessary for the evaluation of the expression.
     * @return the result of evaluating the expression, whose type can vary based on the expression's nature.
     */
    Object evaluate(RuntimeEnvironment env);
}
