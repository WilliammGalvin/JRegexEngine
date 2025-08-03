package me.williamgalvin.engine.vm.instructions;

import me.williamgalvin.engine.vm.OpCode;

public class CharInstruction extends Instruction {

    private final char value;

    public CharInstruction(char value) {
        super(OpCode.CHAR);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CHAR '" + value + "'";
    }
}
