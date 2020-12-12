package uk.co.swilson.advent.day12;

import uk.co.swilson.advent.Solver;

public class Day12 implements Solver {
    public long solvePartOne(String input) {
        Navigator navigator = new Navigator();
        input.lines().map(Instruction::new)
                .forEach(i -> navigator.processInstruction(i));
        return navigator.distanceFromOrigin();
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}

