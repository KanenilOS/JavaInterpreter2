package org.interpreter.exceptions;

/**
 * Represents a runtime exception that can occur during the execution of the interpreter.
 * This exception is used to handle errors that are specific to the execution environment
 * of the interpreter, such as type mismatches, division by zero, or accessing uninitialized variables.
 *
 * <p>Extending {@link RuntimeException}, this class provides functionality to throw unchecked
 * exceptions with a custom message that includes a prefix to indicate that it's a runtime error,
 * helping to distinguish it from other runtime exceptions that might not be specific to the interpreter.</p>
 */
public class InterpreterRuntimeException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The message is modified to include a prefix that indicates the nature of the error
     * as related specifically to the interpreter's execution.
     *
     * @param message the detail message. The detail message is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method.
     */
    public InterpreterRuntimeException(String message) {
        super(String.format("Runtime error: %s", message));
    }
}
