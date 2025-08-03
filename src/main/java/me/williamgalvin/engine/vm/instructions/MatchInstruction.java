package me.williamgalvin.engine.vm.instructions;

import me.williamgalvin.engine.vm.OpCode;

public class MatchInstruction extends Instruction {

    public MatchInstruction() {
        super(OpCode.MATCH);
    }

    @Override
    public String toString() {
        return "MATCH";
    }
}
