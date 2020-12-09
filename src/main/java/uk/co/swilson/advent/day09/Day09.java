package uk.co.swilson.advent.day09;

import uk.co.swilson.advent.Solver;

import java.util.Collection;
import java.util.stream.Collectors;

public class Day09 implements Solver {
    private final int preambleSize;

    public Day09() {
        this(25);
    }

    public Day09(int preambleSize) {
        this.preambleSize = preambleSize;
    }

    public long solvePartOne(String input) {
        var cipher = input.lines()
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());
        var preamble = cipher.subList(0, preambleSize);
        for (var c : cipher.stream().skip(preambleSize).collect(Collectors.toList())) {
            if (!isValid(c, preamble)) {
                return c;
            }
            preamble.remove(0);
            preamble.add(c);
        }
        return -1;
    }

    public boolean isValid(long number, Collection<Long> preamble) {
        var sorted = preamble.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < preamble.size(); ++i) {
            var low = sorted.get(i);
            for (int j = i + 1; j < preamble.size(); ++j) {
                var sum = low + sorted.get(j);
                if (sum == number) {
                    return true;
                }
                if (sum > number) {
                    // bigger values of j won't help now
                    break;
                }
            }
        }
        return false;
    }

    public long solvePartTwo(String input) {
        var key = solvePartOne(input);
        var cipher = input.lines()
                .map(s -> Long.parseLong(s))
                .collect(Collectors.toList());

        for (int rangeStart = 0; rangeStart < cipher.size(); ++rangeStart) {
            int rangeSize = 1;
            long rangeSum = 0;
            // rangeEnd = rangeSum + rangeSize - 1; // Range is inclusive of the start
            while (rangeStart + rangeSize - 1 < cipher.size() && rangeSum < key) {
                ++rangeSize;
                rangeSum = cipher.stream()
                        .skip(rangeStart)
                        .limit(rangeSize)
                        .mapToLong(l -> l)
                        .sum();
            }
            if (rangeSum == key) {
                var range = cipher.stream()
                        .skip(rangeStart)
                        .limit(rangeSize)
                        .collect(Collectors.toList());
                var min = range.stream().mapToLong(l -> l).min().getAsLong();
                var max = range.stream().mapToLong(l -> l).max().getAsLong();
                return min + max;
            }
        }
        return -1;
    }
}
