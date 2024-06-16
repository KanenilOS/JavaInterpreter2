package org.interpreter.parser.statements;

import org.interpreter.runtime.RuntimeEnvironment;
import org.interpreter.parser.expressions.Expression;
import java.util.List;

/**
 * Represents a "for" loop statement in the interpreted language.
 * This class handles looping over a block of statements for a specified range of values,
 * which are determined by start and end expressions. The loop increments a specified
 * variable through the range from the start value to the end value, inclusive, executing
 * a block of statements for each iteration.
 */
public class ForStatement implements Statement {
    private final String variableName;
    private final Expression startExpression;
    private final Expression endExpression;
    private final List<Statement> body;

    /**
     * Constructs a ForStatement with specified control expressions and a block of statements.
     *
     * @param variableName the name of the loop control variable.
     * @param startExpression the expression that determines the starting value of the loop.
     * @param endExpression the expression that determines the ending value of the loop.
     * @param body a list of statements that form the body of the loop, to be executed in each iteration.
     */
    public ForStatement(String variableName, Expression startExpression, Expression endExpression, List<Statement> body) {
        this.variableName = variableName;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.body = body;
    }

    /**
     * Executes the for loop within the given runtime environment.
     * The method evaluates both the start and end expressions to determine the loop's range,
     * sets the loop variable to each integer value in the range, and executes the loop body for each value.
     * The loop variable's value is updated in the environment at the start of each iteration.
     *
     * @param env the runtime environment in which the loop variable and body are executed.
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        int start = ((Double) startExpression.evaluate(env)).intValue();
        int end = ((Double) endExpression.evaluate(env)).intValue();

        for (int i = start; i <= end; i++) {
            env.setVariable(variableName, (double) i);
            for (Statement stmt : body) {
                stmt.execute(env);
            }
        }
    }
}
