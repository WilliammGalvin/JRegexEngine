package me.williamgalvin.engine.parser;

import me.williamgalvin.engine.lexer.Token;
import me.williamgalvin.engine.lexer.TokenType;
import me.williamgalvin.engine.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private ArrayList<Token> tokens;
    private int position;

    private Token peek(int lookahead) {
        if (position + lookahead >= tokens.size()) return null;
        return tokens.get(position + lookahead);
    }

    private Token peek() {
        return peek(0);
    }

    private Token consume() {
        if (position >= tokens.size()) return null;
        return tokens.get(position++);
    }

    private void advance() {
        if (position >= tokens.size())
            throw new RuntimeException("Cannot advance beyond the end of the token list.");

        position++;
    }

    private boolean expect(TokenType type) {
        if (peek() == null) return false;
        return peek().getType() == type;
    }

    private boolean isConcatBoundary() {
        return peek() == null || expect(TokenType.PIPE) || expect(TokenType.RIGHT_PAREN);
    }

    private ASTNode parseAlternation() {
        ASTNode left = parseConcatenation();
        if (left == null) return null;
        ArrayList<ASTNode> children = new ArrayList<>(List.of(left));

        while (expect(TokenType.PIPE)) {
            advance(); // Consume pipe token
            ASTNode right = parseConcatenation();

            if (right == null) {
                throw new RuntimeException("Expected concatenation after '|'");
            }

            children.add(right);
        }

        if (children.size() == 1)
            return children.get(0);

        return new Alternation(children);
    }

    private ASTNode parseConcatenation() {
        ASTNode left = parseRepetition();
        if (left == null) return null;
        ArrayList<ASTNode> children = new ArrayList<>(List.of(left));

        while (!isConcatBoundary()) {
            ASTNode right = parseRepetition();
            if (right == null) break;
            children.add(right);
        }

        if (children.size() == 1) {
            return children.get(0);
        }

        return new Concat(children);
    }

    private ASTNode parseRepetition() {
        ASTNode primary = parsePrimary();
        if (primary == null) return null;
        if (peek() == null) return primary;

        return switch (peek().getType()) {
            case STAR -> {
                advance();
                yield new KleeneStar(primary);
            }
            case PLUS -> {
                advance();
                yield new KleenePlus(primary);
            }
            case QUESTION_MARK -> {
                advance();
                yield new Optional(primary);
            }

            default -> primary;
        };
    }

    private ASTNode parsePrimary() {
        if (peek() == null) return null;

        if (expect(TokenType.CHAR)) {
            Token token = consume();

            if (token == null)
                throw new RuntimeException("Unexpected end of input after CHAR");

            if (token.getValue().length() != 1)
                throw new RuntimeException("Expected a single character but found: " + token.getValue());

            return new Literal(token.getValue().charAt(0));
        }

        if (!expect(TokenType.LEFT_PAREN))
            throw new RuntimeException("Expected character or '(' but found: " + peek().getType());

        advance(); // Consume the left parenthesis
        ASTNode regex = parseAlternation();

        if (regex == null)
            throw new RuntimeException("Expected body after '('");

        if (!expect(TokenType.RIGHT_PAREN))
            throw new RuntimeException("Expected ')' but found: " + peek().getType());

        advance(); // Consume the right parenthesis
        return regex;
    }

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
    }

    public ASTNode buildAST() {
        ASTNode expr = parseAlternation();

        if (position != tokens.size())
            throw new RuntimeException("Unexpected token at end of input: " + peek());

        return expr;
    }

    public ASTNode buildAST(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.position = 0;
        return buildAST();
    }

}
