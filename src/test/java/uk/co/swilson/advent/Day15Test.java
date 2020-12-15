package uk.co.swilson.advent;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.co.swilson.advent.day15.Day15;

import java.util.stream.Stream;

public class Day15Test {
    public static Stream<? extends Arguments> partOneArguments() {
        return Stream.of(
                Arguments.of("0,3,6", 436),
                Arguments.of("1,3,2", 1),
                Arguments.of("2,1,3", 10),
                Arguments.of("1,2,3", 27),
                Arguments.of("2,3,1", 78),
                Arguments.of("3,2,1", 438),
                Arguments.of("3,1,2", 1836)
        );
    }

    public static Stream<? extends Arguments> partTwoArguments() {
        return Stream.of(
                Arguments.of("0,3,6", 175594),
                Arguments.of("1,3,2", 2578),
                Arguments.of("2,1,3", 3544142),
                Arguments.of("1,2,3", 261214),
                Arguments.of("2,3,1", 6895259),
                Arguments.of("3,2,1", 18),
                Arguments.of("3,1,2", 362)
        );
    }

    @ParameterizedTest
    @MethodSource("partOneArguments")
    public void testPartOne(String input, long expected) {
        var solver = new Day15();
        var result = solver.solvePartOne(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("partTwoArguments")
    public void testPartTwo(String input, long expected) {
        var solver = new Day15();
        var result = solver.solvePartTwo(input);
        AssertionsForClassTypes.assertThat(result).isEqualTo(expected);
    }
}
