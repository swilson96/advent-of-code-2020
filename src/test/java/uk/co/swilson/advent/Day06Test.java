package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day06.Day06;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day06Test {
    private static final String EXAMPLE_INPUT = "abc\n" +
            "\n" +
            "a\n" +
            "b\n" +
            "c\n" +
            "\n" +
            "ab\n" +
            "ac\n" +
            "\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "a\n" +
            "\n" +
            "b";

    @Test
    public void exampleGroup() {
        var solver = new Day06();
        var result = solver.countUnionQuestionsInGroup("abcx\n" +
                "abcy\n" +
                "abcz");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void partOneExample() {
        var solver = new Day06();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(11);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day06();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(6);
    }
}
