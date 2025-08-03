package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;
import me.williamgalvin.engine.vm.instructions.JumpInstruction;
import me.williamgalvin.engine.vm.instructions.SplitInstruction;

import java.util.ArrayList;
import java.util.List;

public class Alternation extends ASTNode {

    private ArrayList<ASTNode> children;

    public Alternation(ArrayList<ASTNode> children) {
        this.children = children;
    }

    private int compileTwoAlternatives(List<Instruction> instructions) {
        // 0: SPLIT 1, 3
        // 1: (first child instr)
        // 2: JMP 4
        // 3: (second child instr)
        // 4: ...

        int splitPos = instructions.size();
        instructions.add(new SplitInstruction(-1, -1));

        int firstChildStart = children.get(0).compile(instructions);

        int jumpPos = instructions.size();
        instructions.add(new JumpInstruction(-1));

        int secondChildStart = children.get(1).compile(instructions);

        ((SplitInstruction) instructions.get(splitPos)).setTarget1(firstChildStart);
        ((SplitInstruction) instructions.get(splitPos)).setTarget2(secondChildStart);
        ((JumpInstruction) instructions.get(jumpPos)).setTarget(instructions.size());

        return splitPos;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        // 0: SPLIT 1, 3
        // 1: (child 1 instr)
        // 2: JMP 4
        // 3: (child 2 instr)
        // 4: ...

        if (children.size() == 2) {
            return compileTwoAlternatives(instructions);
        }

        int firstSplitPos = instructions.size();
        instructions.add(new SplitInstruction(-1, -1));

        int prevSplitPos = firstSplitPos;
        int prevJumpPos = -1;

        int altStart = children.get(0).compile(instructions);
        ((SplitInstruction) instructions.get(firstSplitPos)).setTarget1(altStart);

        for (int i = 1; i < children.size(); i++) {
            instructions.add(new JumpInstruction(-1));
            prevJumpPos = instructions.size() - 1;

            instructions.add(new SplitInstruction(-1, -1));
            int currentSplitPos = instructions.size() - 1;

            ((SplitInstruction) instructions.get(prevSplitPos)).setTarget2(currentSplitPos);

            altStart = children.get(i).compile(instructions);

            ((SplitInstruction) instructions.get(currentSplitPos)).setTarget1(altStart);

            prevSplitPos = currentSplitPos;

            ((JumpInstruction) instructions.get(prevJumpPos)).setTarget(instructions.size());
        }

        ((SplitInstruction) instructions.get(prevSplitPos)).setTarget2(instructions.size());

        return firstSplitPos;
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Alternation{" +
                "children=" + children +
                '}';
    }
}
