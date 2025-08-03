package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;
import me.williamgalvin.engine.vm.instructions.SplitInstruction;

import java.util.List;

public class Optional extends ASTNode {

    private ASTNode child;

    public Optional(ASTNode child) {
        this.child = child;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        // 0: SPLIT 1, 2
        // 1: (child instr)
        // 2: ...

        int splitPos = instructions.size();
        instructions.add(new SplitInstruction(-1, -1));

        int childStart = child.compile(instructions);
        int after = instructions.size();

        SplitInstruction split = (SplitInstruction) instructions.get(splitPos);
        split.setTarget1(childStart);
        split.setTarget2(after);

        return splitPos;
    }

    public ASTNode getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "Optional{" +
                "child=" + child +
                '}';
    }
}
