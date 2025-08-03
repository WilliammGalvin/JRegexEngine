package me.williamgalvin.engine.parser.ast;

import me.williamgalvin.engine.vm.instructions.Instruction;

import java.util.List;

public abstract class ASTNode {
    public abstract int compile(List<Instruction> instructions);
}
