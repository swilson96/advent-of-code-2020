package uk.co.swilson.advent;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day16.Day16;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day16Test {
    private static final String EXAMPLE_INPUT = "class: 1-3 or 5-7\n" +
            "row: 6-11 or 33-44\n" +
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
    public void partTwoExample() {
        var solver = new Day16();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(0);
    }

    public static Stream<? extends Arguments> partOneArguments() {
        return Stream.of(
                Arguments.of(EXAMPLE_INPUT, 0)
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
        var solver = new Day16();
        var result = solver.solvePartOne(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String input, long expected) {
        var solver = new Day16();
        var result = solver.solvePartTwo(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }
}
