package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day10.Day10;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day10Test {
    private static final String EXAMPLE_INPUT_1 = "16\n" +
            "10\n" +
            "15\n" +
            "5\n" +
            "1\n" +
            "11\n" +
            "7\n" +
            "19\n" +
            "6\n" +
            "12\n" +
            "4";

    private static final String EXAMPLE_INPUT_2 = "28\n" +
            "33\n" +
            "18\n" +
            "42\n" +
            "31\n" +
            "14\n" +
            "46\n" +
            "20\n" +
            "48\n" +
            "47\n" +
            "24\n" +
            "23\n" +
            "49\n" +
            "45\n" +
            "19\n" +
            "38\n" +
            "39\n" +
            "11\n" +
            "1\n" +
            "32\n" +
            "25\n" +
            "35\n" +
            "8\n" +
            "17\n" +
            "7\n" +
            "9\n" +
            "4\n" +
            "2\n" +
            "34\n" +
            "10\n" +
            "3";

    @Test
    public void example() {
        var solver = new Day10();
        var result = solver.solvePartOne(EXAMPLE_INPUT_1);
        assertThat(result).isEqualTo(35);
    }

    @Test
    public void longerExample() {
        var solver = new Day10();
        var result = solver.solvePartOne(EXAMPLE_INPUT_2);
        assertThat(result).isEqualTo(220);
    }

    @Test
    public void noAdaptors() {
        var solver = new Day10();
        var result = solver.solvePartOne("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void oneAdaptor() {
        var solver = new Day10();
        var result = solver.solvePartOne("3");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void twoAdaptors() {
        var solver = new Day10();
        var result = solver.solvePartOne("6\n3");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void threeAdaptors() {
        var solver = new Day10();
        var result = solver.solvePartOne("2\n6\n3");
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day10();
        var result = solver.solvePartTwo(EXAMPLE_INPUT_1);
        assertThat(result).isEqualTo(0);
    }
}
