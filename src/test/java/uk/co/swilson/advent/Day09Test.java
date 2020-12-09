package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day09.Day09;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day09Test {
    private static final String EXAMPLE_INPUT = "35\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "47\n" +
            "40\n" +
            "62\n" +
            "55\n" +
            "65\n" +
            "95\n" +
            "102\n" +
            "117\n" +
            "150\n" +
            "182\n" +
            "127\n" +
            "219\n" +
            "299\n" +
            "277\n" +
            "309\n" +
            "576";

    @Test
    public void example() {
        var solver = new Day09(5);
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(127);
    }

    @Test
    public void validNumbersForExamplePreamble() {
        var solver = new Day09();
        var preamble = LongStream.rangeClosed(1, 25).boxed().collect(Collectors.toList());

        assertThat(solver.isValid(1, preamble)).isFalse();
        assertThat(solver.isValid(2, preamble)).isFalse();
        assertThat(solver.isValid(3, preamble)).isTrue();
        assertThat(solver.isValid(26, preamble)).isTrue();
        assertThat(solver.isValid(49, preamble)).isTrue();
        assertThat(solver.isValid(50, preamble)).isFalse();
        assertThat(solver.isValid(100, preamble)).isFalse();
    }

    @Test
    public void partTwoExample() {
        var solver = new Day09(5);
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(62);
    }
}
