package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day02.Day02;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day02Test {
    private static final String EXAMPLE_INPUT = "1-3 a: abcde\n" +
            "1-3 b: cdefg\n" +
            "2-9 c: ccccccccc";

    @Test
    public void example() {
        var solver = new Day02();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day02();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(1);
    }
}
