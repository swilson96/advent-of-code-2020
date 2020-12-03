package uk.co.swilson.advent;

import org.junit.Test;
import uk.co.swilson.advent.day01.Day01;

import static org.junit.Assert.*;

public class Day01Test {
    private static final String EXAMPLE_INPUT = "1721\n" +
            "979\n" +
            "366\n" +
            "299\n" +
            "675\n" +
            "1456";

    @Test
    public void example() {
        var solver = new Day01();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertEquals(514579, result);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day01();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertEquals(241861950, result);
    }
}
