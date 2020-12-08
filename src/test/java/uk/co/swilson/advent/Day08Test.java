package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day08.Day08;
import uk.co.swilson.advent.dayXX.DayXX;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day08Test {
    private static final String EXAMPLE_INPUT = "nop +0\n" +
            "acc +1\n" +
            "jmp +4\n" +
            "acc +3\n" +
            "jmp -3\n" +
            "acc -99\n" +
            "acc +1\n" +
            "jmp -4\n" +
            "acc +6";

    @Test
    public void example() {
        var solver = new Day08();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day08();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(0);
    }
}
