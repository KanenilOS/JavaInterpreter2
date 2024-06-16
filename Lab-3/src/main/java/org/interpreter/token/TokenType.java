package org.interpreter.token;

/**
 * Defines the various types of tokens that can be recognized by the lexer
 * in the source code of the programming language being interpreted.
 * Each token type represents a specific category of lexical unit, ranging from
 * symbols and operators to identifiers and control characters. These types are
 * essential for the syntactic classification needed during the parsing process.
 */
public enum TokenType {
    /** Represents punctuation or a special character used to separate statements and other syntactic elements. */
    DELIMITER,

    /** Represents a variable identifier. */
    VARIABLE,

    /** Represents numeric literals, including integers and floating-point numbers. */
    NUMBER,

    /** Represents predefined commands or keywords in the language. */
    COMMAND,

    /** Represents string literals enclosed in quotes. */
    QUOTEDSTR,

    /** Represents the end of a line in the source code, typically used to handle line-based syntax. */
    EOL,

    /** Represents tokens that do not fit any other defined categories. */
    UNKNOWN,

    /** Represents a semicolon (';'), often used as a statement terminator. */
    SEMICOLON,

    /** Represents the plus operator ('+'). */
    PLUS,

    /** Represents the minus operator ('-'). */
    MINUS,

    /** Represents the multiplication operator ('*'). */
    MULTIPLY,

    /** Represents the division operator ('/'). */
    DIVISION,

    /** Represents an open parenthesis ('('), used for grouping expressions. */
    OPEN_PAREN,

    /** Represents a close parenthesis (')'), used for closing grouping expressions. */
    CLOSE_PAREN,

    /** Represents the modulus operator ('%'), used for computing the remainder of division. */
    MODULUS,

    /** Represents the less-than operator. */
    LESS_THAN,

    /** Represents the greater-than operator ('>'). */
    GREATER_THAN,

    /** Represents the equality operator ('='), often used in assignments and comparisons. */
    EQUAL,

    /** Represents the not-equal operator. */
    NOT_EQUAL,

    /** Represents the greater-than-or-equal-to operator ('>='). */
    GREATER_EQUAL,

    /** Represents the less-than-or-equal-to operator. */
    LESS_EQUAL,

    /** Represents a comma (','), often used to separate items in a list. */
    COMMA,

    /** Represents a label identifier, typically used in GOTO statements and label declarations. */
    LABEL,

    /** Represents the end-of-file marker, indicating no more tokens are available for processing. */
    EOF
}
