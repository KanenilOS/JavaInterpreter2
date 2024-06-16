package org.interpreter.lexer;

import org.interpreter.exceptions.InterpreterRuntimeException;
import org.interpreter.token.Token;
import org.interpreter.token.TokenType;

/**
 * The BasicLexer class is responsible for breaking down the input script into tokens
 * that the parser can understand. This includes identifying different types of tokens
 * such as identifiers, numbers, strings, and various operators or delimiters.
 */
public class BasicLexer implements Lexer {
    private final String input;
    private final int length;
    private int position = 0;
    private int lineNumber = 1;

    /**
     * Constructs a lexer with the provided input source code.
     *
     * @param input the source code to be tokenized.
     */
    public BasicLexer(String input) {
        this.input = input;
        this.length = input.length();
    }

    /**
     * Returns the next token from the source code, or an EOF token if the end of the input is reached.
     *
     * @return the next token in the sequence, or EOF if no more tokens are available.
     */
    @Override
    public Token nextToken() {
        skipWhitespace();

        if (position >= length) {
            return new Token("", TokenType.EOF, lineNumber);
        }

        char currentChar = input.charAt(position);
        if (Character.isDigit(currentChar)) return numberToken();
        if (Character.isLetter(currentChar)) return identifierOrLabelToken();
        if (currentChar == '"') return stringToken();

        return handleOperatorOrDelimiter(currentChar);
    }

    /**
     * Handles single character operators or delimiters and advances the position.
     *
     * @param currentChar the character to handle.
     * @return the token corresponding to the operator or delimiter.
     */
    private Token handleOperatorOrDelimiter(char currentChar) {
        switch (currentChar) {
            case ';', ',', '\n', '+', '-', '*', '/', '%', '(', ')', '=' -> {
                return simpleTokenAdvance(currentChar);
            }
            case '>', '<' -> {
                return handleComparisonOperators(currentChar);
            }
            default -> {
                position++;
                return new Token(String.valueOf(currentChar), TokenType.UNKNOWN, lineNumber);
            }
        }
    }

    /**
     * Handles comparison operators such as >, <, >=, <=, <>, etc.
     *
     * @param symbol the comparison operator's starting character.
     * @return the token for the comparison operator.
     */
    private Token simpleTokenAdvance(char symbol) {
        position++;
        TokenType type = switch (symbol) {
            case ';' -> TokenType.SEMICOLON;
            case ',' -> TokenType.COMMA;
            case '\n' -> TokenType.EOL;
            case '+' -> TokenType.PLUS;
            case '-' -> TokenType.MINUS;
            case '*' -> TokenType.MULTIPLY;
            case '/' -> TokenType.DIVISION;
            case '%' -> TokenType.MODULUS;
            case '(' -> TokenType.OPEN_PAREN;
            case ')' -> TokenType.CLOSE_PAREN;
            case '=' -> TokenType.EQUAL;
            default -> TokenType.UNKNOWN;
        };
        return new Token(String.valueOf(symbol), type, lineNumber);
    }

    /**
     * Handles comparison operators such as >, <, >=, <=, and <>.
     *
     * @param currentChar the comparison operator's starting character.
     * @return the token for the comparison operator.
     */
    private Token handleComparisonOperators(char currentChar) {
        char nextChar = position + 1 < length ? input.charAt(position + 1) : '\0';
        position++;
        if (nextChar == '=' && (currentChar == '>' || currentChar == '<')) {
            position++;
            return new Token(currentChar + "=", currentChar == '>' ? TokenType.GREATER_EQUAL : TokenType.LESS_EQUAL, lineNumber);
        } else if (nextChar == '>' && currentChar == '<') {
            position++;
            return new Token("<>", TokenType.NOT_EQUAL, lineNumber);
        }
        return new Token(String.valueOf(currentChar), currentChar == '>' ? TokenType.GREATER_THAN : TokenType.LESS_THAN, lineNumber);
    }

    /**
     * Identifies identifiers or labels from the input, and differentiates between commands, variables, or labels.
     *
     * @return a token representing an identifier, variable, command, or label.
     */
    private Token identifierOrLabelToken() {
        StringBuilder identifier = new StringBuilder();
        while (position < length && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
            identifier.append(input.charAt(position++));
        }
        String idStr = identifier.toString();
        if (position < length && input.charAt(position) == ':') {
            position++;
            return new Token(idStr, TokenType.LABEL, lineNumber);
        }
        return new Token(idStr, Command.fromString(idStr) != null ? TokenType.COMMAND : TokenType.VARIABLE, lineNumber);
    }

    /**
     * Parses numbers, handling integers and floating-point numbers.
     *
     * @return a token representing a number.
     */
    private Token numberToken() {
        StringBuilder number = new StringBuilder();
        boolean hasDecimal = false;
        while (position < length && (Character.isDigit(input.charAt(position)) || (input.charAt(position) == '.' && !hasDecimal))) {
            if (input.charAt(position) == '.') hasDecimal = true;
            number.append(input.charAt(position++));
        }
        return new Token(number.toString(), TokenType.NUMBER, lineNumber);
    }

    /**
     * Parses string literals enclosed in double quotes.
     *
     * @return a token representing a string literal.
     */
    private Token stringToken() {
        position++;
        StringBuilder string = new StringBuilder();
        while (position < length && input.charAt(position) != '"') {
            string.append(input.charAt(position++));
        }
        if (position == length) {
            throw new InterpreterRuntimeException(String.format("Missing closing quote for string. At line %s", lineNumber));
        }
        position++;
        return new Token(string.toString(), TokenType.QUOTEDSTR, lineNumber);
    }

    /**
     * Skips whitespace in the input, updating the position and line number accordingly.
     */
    private void skipWhitespace() {
        while (position < length && Character.isWhitespace(input.charAt(position))) {
            if (input.charAt(position) == '\n') {
                lineNumber++;
                position++;
            } else {
                position++;
            }
        }
    }

    /**
     * Resets the lexer to the beginning of the input. This is useful for multiple passes over the input or reinitialization.
     */
    @Override
    public void reset() {
        position = 0;
        lineNumber = 1;
    }
}
