package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day01.Day01;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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
        assertThat(result).isEqualTo(514579);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day01();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(241861950);
    }
}
