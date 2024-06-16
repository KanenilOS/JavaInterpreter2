package org.interpreter.parser.statements;

import org.interpreter.runtime.RuntimeEnvironment;
import org.interpreter.parser.expressions.Expression;
import java.util.List;

/**
 * Represents a conditional statement in the interpreted language,
 * allowing execution of different blocks of statements based on the evaluation
 * result of a specified condition. This class supports the typical if-else
 * structure found in many programming languages.
 */
public class IfStatement implements Statement {
    private final Expression condition;
    private final List<Statement> trueBranch;
    private final List<Statement> falseBranch;

    /**
     * Constructs an IfStatement with a condition and branches for both the true and false outcomes.
     *
     * @param condition the condition expression that determines which branch of the statement is executed
     * @param trueBranch a list of statements to be executed if the condition evaluates to true
     * @param falseBranch a list of statements to be executed if the condition evaluates to false, may be null if no false branch is specified
     */
    public IfStatement(Expression condition, List<Statement> trueBranch, List<Statement> falseBranch) {
        this.condition = condition;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    /**
     * Executes the appropriate block of statements based on the evaluation result of the condition.
     * This method first evaluates the condition; if true, it executes the true branch; if false, it executes the false branch, if one exists.
     *
     * @param env the runtime environment in which the condition and the statement branches are executed
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        Object result = condition.evaluate(env);
        // Ensure the result is a Boolean before attempting to cast
        if (result instanceof Boolean && (Boolean) result) {
            for (Statement stmt : trueBranch) {
                stmt.execute(env);
            }
        } else if (falseBranch != null) {
            for (Statement stmt : falseBranch) {
                stmt.execute(env);
            }
        }
    }
}
