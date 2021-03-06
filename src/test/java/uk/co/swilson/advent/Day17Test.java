package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day17.Day17;

import java.io.InputStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day17Test {
    private static final String EXAMPLE_INPUT = ".#.\n" +
            "..#\n" +
            "###";

    @Test
    public void example() {
        var solver = new Day17();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(112);
    }

    @Test
    public void partOneIsRight() throws Exception {
        var solver = new Day17();
        InputStream is = Day17.class.getClassLoader().getResourceAsStream("input17.txt");
        var input = new String(is.readAllBytes());
        var result = solver.solvePartOne(input);
        assertThat(result).isEqualTo(310);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day17();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(848);
    }
}
