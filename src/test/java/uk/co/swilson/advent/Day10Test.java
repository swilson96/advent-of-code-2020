package uk.co.swilson.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day10.Day10;

import java.util.stream.Stream;

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

    public static Stream<? extends Arguments> partOneArguments() {
        return Stream.of(
                Arguments.of(EXAMPLE_INPUT_1, 35),
                Arguments.of(EXAMPLE_INPUT_2, 220),
                Arguments.of("", 0),
                Arguments.of("3", 0),
                Arguments.of("6\n3", 0),
                Arguments.of("2\n6\n3", 2)
        );
    }

    public static Stream<? extends Arguments> partTwoArguments() {
        return Stream.of(
                Arguments.of(EXAMPLE_INPUT_1, 8),
                Arguments.of(EXAMPLE_INPUT_2, 19208),
                Arguments.of("", 1),
                Arguments.of("3", 1),
                Arguments.of("6\n3", 1),
                Arguments.of("2\n6\n3", 2)
        );
    }

    @ParameterizedTest
    @MethodSource("partOneArguments")
    public void testPartOne(String input, long expected) {
        var solver = new Day10();
        var result = solver.solvePartOne(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String input, long expected) {
        var solver = new Day10();
        var result = solver.solvePartTwo(input);
        assertThat(result).isEqualTo(expected);
    }
}
