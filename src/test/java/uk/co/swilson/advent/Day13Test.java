package uk.co.swilson.advent;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day13.Day13;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day13Test {
    private static final String EXAMPLE_INPUT = "939\n" +
            "7,13,x,x,59,x,31,19";

    @Test
    public void example() {
        var solver = new Day13();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(295);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day13();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(1068781);
    }

    public static Stream<? extends Arguments> partTwoArguments() {
        return Stream.of(
                Arguments.of("17,x,13,19", 3417),
                Arguments.of("67,7,59,61", 754018),
                Arguments.of("67,x,7,59,61", 779210),
                Arguments.of("67,7,x,59,61", 1261476),
                Arguments.of("1789,37,47,1889", 1202161486)
        );
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String schedule, long expected) {
        var input = "939\n" + schedule;
        var solver = new Day13();
        var result = solver.solvePartTwo(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }
}
