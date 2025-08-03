package me.williamgalvin.engine.vm.instructions;

import me.williamgalvin.engine.vm.OpCode;

public class JumpInstruction extends Instruction {

    private int target;

    public JumpInstruction(int target) {
        super(OpCode.JMP);
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "JMP " + target;
    }
}