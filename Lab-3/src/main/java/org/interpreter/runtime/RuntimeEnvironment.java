package org.interpreter.runtime;

import org.interpreter.exceptions.UninitializedSubroutineException;
import org.interpreter.exceptions.UninitializedVariableException;
import org.interpreter.parser.statements.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the runtime environment of the interpreter, handling variable values and control flow mechanisms.
 * This class maintains mappings for both variables and labeled statements, allowing the interpreter
 * to execute based on dynamic control flow and variable values set during runtime.
 */
public class RuntimeEnvironment {

    /** Stores variable names and their current values. */
    private final Map<String, Object> variables = new HashMap<>();

    /** Maps labels to lists of statements, facilitating control flow changes such as GOTO or subroutine calls. */
    private final Map<String, List<Statement>> statements = new HashMap<>();

    /**
     * Default constructor for the RuntimeEnvironment class.
     * Initializes the environment with empty maps for variables and subroutine statements.
     */
    public RuntimeEnvironment() {
        // Default constructor
    }

    /**
     * Registers a label with a corresponding list of statements.
     * This is typically used to handle subroutine declarations or labeled blocks of code.
     *
     * @param label the label that identifies the block of statements
     * @param dimension the list of statements associated with the label
     */
    public void registerLabel(String label, List<Statement> dimension) {
        statements.put(label, dimension);
    }

    /**
     * Retrieves and returns the list of statements associated with a label.
     * Throws an UninitializedSubroutineException if the label is not registered,
     * indicating an attempt to access a non-existent subroutine.
     *
     * @param label the label whose associated statements are to be retrieved
     * @return the list of statements associated with the label
     * @throws UninitializedSubroutineException if no statements are associated with the label
     */
    public List<Statement> goToLabel(String label) {
        List<Statement> statementList = statements.get(label);
        if (statementList == null) {
            throw new UninitializedSubroutineException(label);
        }
        return statementList;
    }

    /**
     * Sets the value of a variable in the runtime environment.
     * This method updates the variable's value if it already exists, or creates a new variable entry if it does not.
     *
     * @param name the name of the variable
     * @param value the value to be set for the variable
     */
    public void setVariable(String name, Object value) {
        variables.put(name, value);
    }

    /**
     * Retrieves the value of a variable from the runtime environment.
     * Throws an UninitializedVariableException if the variable is not found,
     * indicating an attempt to access an undeclared variable.
     *
     * @param name the name of the variable to retrieve
     * @return the value of the variable
     * @throws UninitializedVariableException if the variable does not exist
     */
    public Object getVariable(String name) {
        if (!variables.containsKey(name)) {
            throw new UninitializedVariableException(name);
        }
        return variables.get(name);
    }
}
