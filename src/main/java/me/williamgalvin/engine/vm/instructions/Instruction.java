package me.williamgalvin.engine.vm.instructions;

import me.williamgalvin.engine.vm.OpCode;

public class Instruction {
    private OpCode opcode;

    public Instruction(OpCode code) {
        this.opcode = code;
    }

    public OpCode getOpcode() {
        return opcode;
    }
}
