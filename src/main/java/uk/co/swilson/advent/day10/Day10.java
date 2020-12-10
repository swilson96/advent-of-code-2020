package uk.co.swilson.advent.day10;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Solver {
    public long solvePartOne(String input) {
        var jumps = inputToJumps(input);

        var singles = jumps.stream().filter(j -> j == 1).count();
        var triples = jumps.stream().filter(j -> j == 3).count();

        return singles * triples;
    }

    private List<Integer> inputToJumps(String input) {
        var adaptors = input.lines()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        List<Integer> jumps = Lists.newArrayList();
        var prev = 0; // socket joltage
        for (var a : adaptors) {
            jumps.add(a - prev);
            prev = a;
        }
        jumps.add(3); // always a jump to the laptop itself
        return jumps;
    }

    public long solvePartTwo(String input) {
        String jumps = inputToJumps(input).stream().map(i -> i.toString()).collect(Collectors.joining());
        return countValidSubsets(jumps);
    }

    private long countValidSubsets(String jumps) {
        var sections = jumps.split("3|22+");
        return Arrays.stream(sections)
                .mapToLong(this::countValidSubsetsNoThrees)
                .reduce(1, (a, b) -> a * b);
    }

    private long countValidSubsetsNoThrees(String jumps) {
        if (jumps.length() < 2) {
            return 1;
        }
        var variations = new String[] {
                (Integer.parseInt(jumps.substring(0, 1)) + Integer.parseInt(jumps.substring(1, 2))) + jumps.substring(2),
                jumps.substring(1)
        };
        return Arrays.stream(variations)
                .mapToLong(this::countValidSubsets)
                .sum();
    }

    // Works for part 2, but too slow
    private long countArrangements(int input, List<Integer> adapters, int targetJoltage) {
        if (adapters.isEmpty()) {
            if (input + 3 != targetJoltage) {
                return 0;
            }
            return 1;
        }

        return adapters.stream()
                .filter(a -> input + 1 <= a && a <= input + 3)
                .mapToLong(next -> {
                    var tail = adapters.stream()
                            .filter(a -> a > next)
                            .collect(Collectors.toList());
                    return countArrangements(next, tail, targetJoltage);
                })
                .sum();
    }
}

