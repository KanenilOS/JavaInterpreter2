package org.interpreter.lexer;

import org.interpreter.token.Token;

/**
 * Defines the interface for a lexer in the interpreter. A lexer (lexical analyzer) is responsible for
 * breaking down the input source code into a series of tokens that are easier for the parser to understand
 * and process. This interface outlines the essential operations that any lexer implementation should provide
 * within the language interpreter.
 */
public interface Lexer {

    /**
     * Retrieves the next token from the source code. This method is used to sequentially
     * read tokens from the input, which are then used by the parser for syntax and semantic analysis.
     * The method must handle lexical analysis such as identifying keywords, operators, literals,
     * and other syntactic elements.
     *
     * @return the next token from the input stream, or a token indicating the end of file (EOF)
     *         if there are no more tokens to read.
     */
    Token nextToken();

    /**
     * Resets the lexer to the beginning of the input. This method is used to reinitialize the lexer's
     * state, allowing the input to be reanalyzed from the start. It is useful in scenarios where
     * multiple passes over the input are necessary, or when the parsing strategy requires revisiting
     * the input from the beginning.
     */
    void reset();
}
