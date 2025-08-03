package me.williamgalvin.engine.vm;

import me.williamgalvin.engine.vm.instructions.*;

import java.util.*;

public class Matcher {

    public boolean isMatch(final ArrayList<Instruction> instructions, final String input) {
        Deque<ThreadState> queue = new ArrayDeque<>();
        Set<ThreadState> visited = new HashSet<>();

        queue.add(new ThreadState(0, 0));

        while (!queue.isEmpty()) {
            ThreadState thread = queue.poll();
            if (!visited.add(thread)) continue;

            int ip = thread.ip();
            int sp = thread.sp();

            if (ip >= instructions.size())
                continue;

            Instruction instr = instructions.get(ip);

            if (instr instanceof CharInstruction charInstr) {
                if (sp < input.length() && input.charAt(sp) == charInstr.getValue())
                    queue.add(new ThreadState(ip + 1, sp + 1));

                continue;
            }

            if (instr instanceof SplitInstruction splitInstr) {
                queue.add(new ThreadState(splitInstr.getTarget1(), sp));
                queue.add(new ThreadState(splitInstr.getTarget2(), sp));
                continue;
            }

            if (instr instanceof JumpInstruction jmpInstr) {
                queue.add(new ThreadState(jmpInstr.getTarget(), sp));
                continue;
            }

            if (instr instanceof MatchInstruction)
                return true;
        }

        return false;
    }

}
