package uk.co.swilson.advent.day10;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Solver {
    private int targetJoltage;

    public long solvePartOne(String input) {
        var adaptors = input.lines()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        targetJoltage = adaptors.stream().mapToInt(i -> i).max().orElse(0) + 3;

        var chain = sortAdapters(0, adaptors);

        var numberOfSingleJumps = countJumps(1, chain);
        var numberOfDoubleJumps = countJumps(3, chain);

        return numberOfSingleJumps * numberOfDoubleJumps;
    }

    private List<Integer> sortAdapters(int input, List<Integer> adapters) {
        if (adapters.isEmpty()) {
            if (input + 3 != targetJoltage) {
                throw new NoSolutionException();
            }
            return adapters;
        }

        var next = adapters.stream()
                .filter(a -> input + 1 <= a && a <= input + 3)
                .mapToInt(i -> i)
                .min()
                .orElseThrow(() -> new NoSolutionException());
        var tail = adapters.stream().filter(a -> a != next).collect(Collectors.toList());
        var solution = Lists.newArrayList(next);
        solution.addAll(sortAdapters(next, tail));
        return solution;
    }

    private int countJumps(int jump, List<Integer> chain) {
        int count = 0;
        var prev = 0;
        for (var a : chain) {
            if (a - prev == jump) {
                ++count;
            }
            prev = a;
        }
        if (targetJoltage - prev == jump) {
            ++count;
        }
        return count;
    }

    public long solvePartTwo(String input) {
        var adaptors = input.lines()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        targetJoltage = adaptors.stream().mapToInt(i -> i).max().orElse(0) + 3;

        return countArrangements(0, adaptors);
    }

    private long countArrangements(int input, List<Integer> adapters) {
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
                    return countArrangements(next, tail);
                })
                .sum();
    }
}

