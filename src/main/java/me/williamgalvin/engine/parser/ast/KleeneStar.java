package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;
import me.williamgalvin.engine.vm.instructions.JumpInstruction;
import me.williamgalvin.engine.vm.instructions.SplitInstruction;

import java.util.List;

public class KleeneStar extends ASTNode {

    private ASTNode child;

    public KleeneStar(ASTNode child) {
        this.child = child;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        // 0: SPLIT 1, 3
        // 1: (child instr)
        // 2: JMP 0
        // 3: ...

        int startPos = instructions.size();
        instructions.add(new SplitInstruction(startPos + 1, -1));

        int bodyStart = child.compile(instructions);
        instructions.add(new JumpInstruction(startPos));

        SplitInstruction split = (SplitInstruction) instructions.get(startPos);
        split.setTarget2(instructions.size());

        return startPos;
    }

    public ASTNode getChild() {
        return child;
    }

    @Override
    public String toString() {
        return "KleeneStar{" +
                "child=" + child +
                '}';
    }
}
