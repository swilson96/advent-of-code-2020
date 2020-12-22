package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day22.Day22;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day22Test {
    private static final String EXAMPLE_INPUT = "Player 1:\n" +
            "9\n" +
            "2\n" +
            "6\n" +
            "3\n" +
            "1\n" +
            "\n" +
            "Player 2:\n" +
            "5\n" +
            "8\n" +
            "4\n" +
            "7\n" +
            "10";

    @Test
    public void example() {
        var solver = new Day22();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(306);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day22();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(291);
    }
}
