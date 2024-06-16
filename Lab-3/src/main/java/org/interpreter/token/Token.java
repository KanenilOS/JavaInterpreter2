package org.interpreter.token;

/**
 * Represents a lexical token within the source code being interpreted.
 * A token is a significant sequence of characters in the source code,
 * categorized by its type, such as an variable, command, symbol, etc.
 * This record also captures the line number in the source file for error
 * reporting and debugging purposes.
 *
 * @param text The literal string of the token, as it appears in the source code.
 * @param type The enumerated type of the token, indicating its category or role within the language grammar.
 * @param lineNumber The line number in the source code where this token starts, aiding in accurate error reporting.
 */
public record Token(String text, TokenType type, int lineNumber) {}