package uk.co.swilson.advent.day08;

import uk.co.swilson.advent.Solver;

import java.util.HashSet;
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
        return 0;
    }

    private class Instruction {
        private final int payload;
        private final String type;

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
    }
}


