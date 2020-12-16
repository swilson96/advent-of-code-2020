package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day16.Day16;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day16Test {
    private static final String EXAMPLE_INPUT = "class: 1-3 or 5-7\n" +
            "departure row: 6-11 or 33-44\n" +
            "seat: 13-40 or 45-50\n" +
            "\n" +
            "your ticket:\n" +
            "7,1,14\n" +
            "\n" +
            "nearby tickets:\n" +
            "7,3,47\n" +
            "40,4,50\n" +
            "55,2,20\n" +
            "38,6,12";

    @Test
    public void example() {
        var solver = new Day16();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(71);
    }

    @Test
    public void examplePartTwo() {
        var solver = new Day16();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(7);
    }
}
