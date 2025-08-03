package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Concat extends ASTNode {

    private ArrayList<ASTNode> children;

    public Concat(ArrayList<ASTNode> children) {
        this.children = children;
    }

    @Override
    public int compile(List<Instruction> instructions) {
        int startIndex = -1;

        for (int i = 0; i < children.size(); i++) {
            int index = children.get(i).compile(instructions);

            if (i == 0)
                startIndex = index;
        }

        return startIndex;
    }

    public ArrayList<ASTNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Concat{" +
                "children=" + children +
                '}';
    }
}