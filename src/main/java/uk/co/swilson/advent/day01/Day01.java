package uk.co.swilson.advent.day01;

import uk.co.swilson.advent.Solver;

import java.util.stream.Collectors;

public class Day01 extends Solver {
    public long solvePartOne(String input) {
        var expenses = input.lines()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < expenses.size(); ++i) {
            var x = expenses.get(i);
            for (int j = i + 1; j < expenses.size(); ++j) {
                var y = expenses.get(j);
                if (x + y == 2020) {
                    return x * y;
                }
            }
        }

        return 0;
    }

    public long solvePartTwo(String input) {
        var expenses = input.lines()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < expenses.size(); ++i) {
            var x = expenses.get(i);
            for (int j = i + 1; j < expenses.size(); ++j) {
                var y = expenses.get(j);
                for (int k = j + 1; k < expenses.size(); ++k) {
                    var z = expenses.get(k);
                    if (x + y + z == 2020) {
                        return x * y * z;
                    }
                }
            }
        }

        return 0;
    }
}
