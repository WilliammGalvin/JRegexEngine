package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;
import me.williamgalvin.engine.vm.instructions.SplitInstruction;

import java.util.List;

public class KleenePlus extends ASTNode {

    private ASTNode child;

    public KleenePlus(ASTNode child) {
        this.child = child;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        // 0: (child instr)
        // 1: SPLIT 0, 2
        // 2: ...

        int startPos = instructions.size();
        int bodyStart = child.compile(instructions);

        int splitPos = instructions.size();
        instructions.add(new SplitInstruction(startPos, -1));

        SplitInstruction split = (SplitInstruction) instructions.get(splitPos);
        split.setTarget2(instructions.size());

        return startPos;
    }

    public ASTNode getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "KleenePlus{" +
                "child=" + child +
                '}';
    }
}
