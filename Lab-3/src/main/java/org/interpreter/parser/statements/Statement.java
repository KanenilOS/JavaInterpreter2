package org.interpreter.parser.statements;

import org.interpreter.runtime.RuntimeEnvironment;

/**
 * Represents a generic statement within the interpreted language.
 * This interface defines the fundamental behavior for all types of statements
 * used in the interpreter, ensuring that they can be executed within a given
 * runtime environment. Implementations of this interface are responsible for
 * defining specific execution behaviors for different kinds of statements,
 * such as assignment, loops, conditionals, etc.
 */
public interface Statement {

    /**
     * Executes this statement within the context of the specified runtime environment.
     * The method is responsible for performing the action defined by the statement,
     * which may involve modifying the environment's state, evaluating expressions,
     * or handling control flow, among other possibilities.
     *
     * @param env the runtime environment in which this statement is executed. It provides
     *            the necessary context and state required for executing the statement.
     */
    void execute(RuntimeEnvironment env);
}
