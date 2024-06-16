package org.interpreter.parser.statements;

import org.interpreter.runtime.RuntimeEnvironment;
import java.util.List;

/**
 * Represents a "goto" statement in the interpreted language, which allows
 * unconditional jumps to a specified label within the program.
 * This control flow statement is used to redirect execution to another part
 * of the program based on a predefined label.
 */
public class GoToStatement implements Statement {
    private final String label;

    /**
     * Constructs a GoToStatement that targets a specific label.
     *
     * @param label the label to which control should jump.
     *              This label corresponds to a predefined location in the program
     *              where a block of statements is defined.
     */
    public GoToStatement(String label) {
        this.label = label;
    }

    /**
     * Executes the goto statement within the given runtime environment by jumping
     * to the block of statements associated with the specified label.
     * It retrieves the list of statements corresponding to the label from the
     * runtime environment and executes them sequentially.
     *
     * @param env the runtime environment in which the current program execution state is maintained.
     *            This environment is used to retrieve and execute the labeled block of statements.
     */
    @Override
    public void execute(RuntimeEnvironment env) {
        // Retrieves the block of statements associated with the label
        List<Statement> statementList = env.goToLabel(label);

        // Executes each statement in the retrieved list
        for (Statement stmt : statementList) {
            stmt.execute(env);
        }
    }
}
