package uk.co.swilson.advent.day12;

import uk.co.swilson.advent.Solver;

public class Day12 extends Solver {
    public long solvePartOne(String input) {
        Navigator navigator = new Navigator(new Ship(Vector.EAST, Vector.ORIGIN));
        input.lines().map(Instruction::new)
                .forEach(navigator::processPartOneInstruction);
        return navigator.distanceFromOrigin();
    }

    public long solvePartTwo(String input) {
        Navigator navigator = new Navigator(new Ship(new Vector(10, 1), Vector.ORIGIN));
        input.lines().map(Instruction::new)
                .forEach(navigator::processPartTwoInstruction);
        return navigator.distanceFromOrigin();
    }
}

