package me.williamgalvin.engine;

import me.williamgalvin.engine.lexer.Lexer;
import me.williamgalvin.engine.lexer.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer("a+b*(c|d)e?");
        lexer.lexInput();

        List<Token> tokens = lexer.getTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

}
