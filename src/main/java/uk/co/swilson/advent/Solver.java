package uk.co.swilson.advent;

public abstract class Solver {
    public abstract long solvePartOne(String input);
    public abstract long solvePartTwo(String input);

    public String solvePartOneToString(String input) {
        return String.valueOf(solvePartOne(input));
    }

    public String solvePartTwoToString(String input) {
        return String.valueOf(solvePartTwo(input));
    }
}
