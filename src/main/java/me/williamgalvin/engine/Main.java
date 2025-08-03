package me.williamgalvin.engine;

import me.williamgalvin.engine.lexer.Lexer;
import me.williamgalvin.engine.lexer.Token;
import me.williamgalvin.engine.parser.Parser;
import me.williamgalvin.engine.parser.ast.ASTNode;
import me.williamgalvin.engine.vm.ByteCodeCompiler;
import me.williamgalvin.engine.vm.Matcher;
import me.williamgalvin.engine.vm.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = new Lexer("a*(b|c|d)+e?");
        lexer.lexInput();

        ArrayList<Token> tokens = lexer.getTokens();
        for (Token token : tokens) {
            System.out.println(token);
        }

        System.out.println("========");

        Parser parser = new Parser(tokens);
        ASTNode program = parser.buildAST();

        System.out.println(program);

        System.out.println("========");

        ArrayList<Instruction> instructions = ByteCodeCompiler.compileProgram(program);
        for (int i = 0; i < instructions.size(); i++) {
            System.out.println(i + ": " + instructions.get(i));
        }

        System.out.println("========");

        Matcher matcher = new Matcher();
        boolean isMatch = matcher.isMatch(instructions, "aaabce");
        System.out.println("Matched: " + isMatch);
    }

}
