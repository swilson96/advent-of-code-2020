package uk.co.swilson.advent.day08;

import uk.co.swilson.advent.Solver;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day08 implements Solver {
    private int acc = 0;

    public int solvePartOne(String input) {
        var lines = input.lines().collect(Collectors.toList());
        var currentIndex = 0;
        var usedIndices = new HashSet<Integer>();

        while (!usedIndices.contains(currentIndex)) {
            usedIndices.add(currentIndex);
            currentIndex = new Instruction(lines.get(currentIndex))
                    .execute(currentIndex);
        }

        return acc;
    }

    public int solvePartTwo(String input) {
        var instructions = input.lines()
                .map(Instruction::new)
                .collect(Collectors.toList());
        for (int indexToFix = 0; indexToFix < instructions.size(); ++indexToFix) {
            if (isCorrectFix(instructions, indexToFix)) {
                return acc;
            }
        }
        return -1;
    }

    private boolean isCorrectFix(List<Instruction> instructions, int indexToFix) {
        acc = 0;
        var currentIndex = 0;
        var usedIndices = new HashSet<Integer>();

        while (!usedIndices.contains(currentIndex)) {
            if (currentIndex == instructions.size()) {
                return true;
            }

            usedIndices.add(currentIndex);
            var currentInstruction = instructions.get(currentIndex);
            if (currentIndex == indexToFix) {
                currentInstruction = currentInstruction.fix();
            }
            currentIndex = currentInstruction.execute(currentIndex);
        }

        return false;
    }

    private class Instruction {
        private final String type;
        private final int payload;

        public Instruction(String type, int payload) {
            this.type = type;
            this.payload = payload;
        }

        public Instruction(String input) {
            var bits = input.split(" ");
            type = bits[0];
            payload = Integer.parseInt(bits[1]);
        }

        // Returns next index
        public int execute(int currentIndex) {
            switch (type) {
                case "nop":
                    return currentIndex + 1;
                case "acc":
                    acc += payload;
                    return currentIndex + 1;
                case "jmp":
                    return currentIndex + payload;
                default:
                    throw new RuntimeException("Instruction type unknown: " + type);
            }
        }

        public Instruction fix() {
            if (type == "acc") {
                return this;
            }
            return new Instruction(type == "nop" ? "jmp" : "nop", payload);
        }
    }
}


