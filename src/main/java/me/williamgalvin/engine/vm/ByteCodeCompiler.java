package me.williamgalvin.engine.vm;

import me.williamgalvin.engine.parser.ast.ASTNode;
import me.williamgalvin.engine.vm.instructions.Instruction;
import me.williamgalvin.engine.vm.instructions.MatchInstruction;

import java.util.ArrayList;

public class ByteCodeCompiler {

    public static ArrayList<Instruction> compileProgram(ASTNode program) {
        ArrayList<Instruction> compiledInstructions = new ArrayList<>();
        program.compile(compiledInstructions);
        compiledInstructions.add(new MatchInstruction());

        return compiledInstructions;
    }

}
