package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.CharInstruction;
import me.williamgalvin.engine.vm.instructions.Instruction;

import java.util.List;

public class Literal extends ASTNode {

    private final char value;

    public Literal(char value) {
        this.value = value;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        // 0: CHAR x

        int index = instructions.size();
        instructions.add(new CharInstruction(this.value));
        return index;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Literal{" +
                "value='" + value + '\'' +
                '}';
    }
}
