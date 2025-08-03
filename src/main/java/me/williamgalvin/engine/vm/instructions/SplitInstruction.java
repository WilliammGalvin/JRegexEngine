package me.williamgalvin.engine.vm.instructions;

import me.williamgalvin.engine.vm.OpCode;

public class SplitInstruction extends Instruction {

    private int target1;
    private int target2;

    public SplitInstruction(int target1, int target2) {
        super(OpCode.SPLIT);
        this.target1 = target1;
        this.target2 = target2;
    }

    public int getTarget1() {
        return target1;
    }

    public void setTarget1(int target1) {
        this.target1 = target1;
    }

    public int getTarget2() {
        return target2;
    }

    public void setTarget2(int target2) {
        this.target2 = target2;
    }

    @Override
    public String toString() {
        return "SPLIT " + target1 + ", " + target2;
    }

}
