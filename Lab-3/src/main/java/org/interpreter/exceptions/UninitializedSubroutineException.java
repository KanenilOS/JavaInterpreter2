package org.interpreter.exceptions;

/**
 * Exception thrown when an attempt is made to call a subroutine that has not been initialized or defined.
 * This typically occurs when the interpreter tries to execute a GOSUB command for a subroutine label
 * that does not exist or has not yet been loaded or parsed.
 *
 * <p>This class extends {@link RuntimeException}, meaning that it is an unchecked exception that
 * does not require explicit handling via try-catch blocks, though it may be caught if needed.</p>
 */
public class UninitializedSubroutineException extends RuntimeException {

    /**
     * Constructs an UninitializedSubroutineException with a specific message indicating
     * which subroutine was not initialized.
     *
     * @param name the name of the subroutine that triggered the exception.
     *             The name is incorporated into the exception message to provide detailed
     *             feedback about the error, which can aid in debugging.
     */
    public UninitializedSubroutineException(String name) {
        super(String.format("Runtime error: Subroutine '%s' not initialized", name));
    }
}
