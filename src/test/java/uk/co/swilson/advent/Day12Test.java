package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day12.Day12;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day12Test {
    private static final String EXAMPLE_INPUT = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11";

    @Test
    public void example() {
        var solver = new Day12();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(25);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day12();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(286);
    }
}
