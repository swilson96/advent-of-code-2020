package uk.co.swilson.advent;

import org.junit.Test;
import uk.co.swilson.advent.day03.Day03;

import static org.junit.Assert.assertEquals;

public class Day03Test {
    private static final String EXAMPLE_INPUT = "..##.......\n" +
            "#...#...#..\n" +
            ".#....#..#.\n" +
            "..#.#...#.#\n" +
            ".#...##..#.\n" +
            "..#.##.....\n" +
            ".#.#.#....#\n" +
            ".#........#\n" +
            "#.##...#...\n" +
            "#...##....#\n" +
            ".#..#...#.#";

    @Test
    public void example() {
        var solver = new Day03();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertEquals(7, result);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day03();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertEquals(336, result);
    }
}
