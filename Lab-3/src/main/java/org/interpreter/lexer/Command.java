package org.interpreter.lexer;

/**
 * Enumerates the commands understood by the interpreter's lexer and parser.
 * Each command represents a specific control structure or action in the interpreted language,
 * such as conditional execution, loops, or input/output operations.
 */
public enum Command {
    /**
     * Command to output text or variable content.
     */
    PRINT,

    /**
     * Begins a conditional block.
     */
    IF,

    /**
     * Specifies the alternative block for an IF statement.
     */
    ELSE,

    /**
     * Starts a for loop.
     */
    FOR,

    /**
     * Directs the flow to a labeled section of the code.
     */
    GOTO,

    /**
     * Calls a subroutine, expecting a RETURN.
     */
    GOSUB,

    /**
     * Follows IF to separate the condition from the block.
     */
    THEN,

    /**
     * Marks the end of an IF block.
     */
    ENDIF,

    /**
     * Used with FOR to specify the range.
     */
    TO,

    /**
     * Marks the end of a FOR loop.
     */
    NEXT,

    /**
     * Returns from a GOSUB call.
     */
    RETURN,

    /**
     * Indicates the end of the program or a block.
     */
    END,

    /**
     * Command to take input and store in a variable.
     */
    INPUT;

    /**
     * Converts a string representation of a command into its corresponding {@link Command} enum constant.
     * This method allows for case-insensitive matching of command strings, facilitating the parsing
     * process where commands are identified from text input.
     *
     * @param commandStr the string representation of the command.
     * @return the {@link Command} constant matching the string, or {@code null} if no match is found.
     */
    public static Command fromString(String commandStr) {
        for (Command command : Command.values()) {
            if (command.name().equalsIgnoreCase(commandStr)) {
                return command;
            }
        }
        return null; // Return null if the command is not recognized
    }
}
