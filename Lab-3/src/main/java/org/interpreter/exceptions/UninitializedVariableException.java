package org.interpreter.exceptions;

/**
 * Exception thrown when an attempt is made to access or use a variable that has not been initialized.
 * This typically occurs when the interpreter encounters a variable reference that has not been assigned a value.
 *
 * <p>This class extends {@link RuntimeException}, making it an unchecked exception that can be used to indicate
 * runtime errors specifically related to variable initialization within the interpreter environment.</p>
 */
public class UninitializedVariableException extends RuntimeException {

    /**
     * Constructs an UninitializedVariableException with a specific message indicating
     * which variable was not initialized.
     *
     * @param name the name of the uninitialized variable that triggered the exception.
     *             The name is included in the exception message to provide detailed
     *             feedback about the error, aiding in debugging and error resolution.
     */
    public UninitializedVariableException(String name) {
        super(String.format("Runtime error: Variable '%s' not initialized", name));
    }
}
