package uk.co.swilson.advent;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day18.Day18;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day18Test {
    public static Stream<? extends Arguments> partOneArguments() {
        return Stream.of(
                Arguments.of("3 * 7 * 9", 189),
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 71),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51),
                Arguments.of("2 * 3 + (4 * 5)", 26),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 437),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 12240),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 13632)
        );
    }

    public static Stream<? extends Arguments> partTwoArguments() {
        return Stream.of(
                Arguments.of("1 + 2 * 3 + 4 * 5 + 6", 231),
                Arguments.of("1 + (2 * 3) + (4 * (5 + 6))", 51),
                Arguments.of("2 * 3 + (4 * 5)", 46),
                Arguments.of("5 + (8 * 3 + 9 + 3 * 4 * 3)", 1445),
                Arguments.of("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", 669060),
                Arguments.of("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", 23340)
        );
    }

    @ParameterizedTest
    @MethodSource("partOneArguments")
    public void testPartOne(String input, long expected) {
        var solver = new Day18();
        var result = solver.solvePartOne(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String input, long expected) {
        var solver = new Day18();
        var result = solver.solvePartTwo(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }
}
