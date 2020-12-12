package uk.co.swilson.advent;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day12.Day12;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day12Test {
    private static final String EXAMPLE_INPUT = "F10\n" +
            "N3\n" +
            "F7\n" +
            "R90\n" +
            "F11";

    @Test
    public void example() {
        var solver = new Day12();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(25);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day12();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(0);
    }

    public static Stream<? extends Arguments> partOneArguments() {
        return Stream.of(
                Arguments.of(EXAMPLE_INPUT, 25)
        );
    }

    public static Stream<? extends Arguments> partTwoArguments() {
        return Stream.of(
                Arguments.of(EXAMPLE_INPUT, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("partOneArguments")
    public void testPartOne(String input, long expected) {
        var solver = new Day12();
        var result = solver.solvePartOne(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String input, long expected) {
        var solver = new Day12();
        var result = solver.solvePartTwo(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }
}
