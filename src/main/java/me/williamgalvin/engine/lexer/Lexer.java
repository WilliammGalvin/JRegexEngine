package me.williamgalvin.engine.lexer;

import java.util.ArrayList;
import java.util.Map;

public class Lexer {

    static Map<Character, TokenType> symbolMap = Map.of(
            '+', TokenType.PLUS,
            '*', TokenType.STAR,
            '?', TokenType.QUESTION_MARK,
            '|', TokenType.PIPE,
            '(', TokenType.LEFT_PAREN,
            ')', TokenType.RIGHT_PAREN,
            '{', TokenType.LEFT_BRACE,
            '}', TokenType.RIGHT_BRACE
    );

    private String input;
    private int position;

    private ArrayList<Token> tokens = new ArrayList<>();

    /* Helper functions */
    private boolean isAtEnd(int lookahead) {
        if (input == null) return true;
        return position + lookahead >= input.length();
    }

    private boolean isAtEnd() {
        return isAtEnd(0);
    }

    private char peek(int lookahead) {
        if (isAtEnd(lookahead)) return '\0';
        return input.charAt(position + lookahead);
    }

    private char peek() {
        return peek(0);
    }

    private void advance(int index) {
        position += index;
    }

    private void advance() {
        advance(1);
    }

    private char consume() {
        char current = peek();
        advance();
        return current;
    }

    private boolean isSymbol(char c) {
        return symbolMap.containsKey(c);
    }

    /* Lexing */
    private Token lexChar() {
        return new Token(TokenType.CHAR, String.valueOf(consume()));
    }

    private Token lexSymbol() {
        char current = consume();
        TokenType type = symbolMap.get(current);

        if (type == null) {
            throw new IllegalArgumentException("Unknown symbol: " + current);
        }

        return new Token(type);
    }

    public void lexInput() {
        while (!isAtEnd()) {
            char current = peek();

            if (Character.isWhitespace(current)) {
                advance();
                continue;
            }

            if (Character.isLetterOrDigit(current))
                tokens.add(lexChar());
            else if (isSymbol(current))
                tokens.add(lexSymbol());
            else
                throw new IllegalArgumentException("Unexpected character: " + current);
        }
    }

    public Lexer(String expr) {
        reset(expr);
    }

    public void reset(String expr) {
        this.input = expr;
        this.position = 0;
        tokens.clear();
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
