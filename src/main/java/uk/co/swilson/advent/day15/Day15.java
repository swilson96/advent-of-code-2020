package uk.co.swilson.advent.day15;

import uk.co.swilson.advent.Solver;

import java.util.HashMap;

public class Day15 extends Solver {
    public long solvePartOne(String input) {
        return getNthTurn(input, 2020);
    }

    public long solvePartTwo(String input) {
        return getNthTurn(input, 30000000);
    }

    private long getNthTurn(String input, int target) {
        var map = new HashMap<Long, Long>();

        long lastSpoken = 0;
        long turn = 1;

        for (var seed : input.split(",")) {
            lastSpoken = Long.parseLong(seed);
            map.put(lastSpoken, turn);
            ++turn;
        }

        while (turn <= target) {
            var thisTurn = turn - 1 - map.getOrDefault(lastSpoken, turn - 1);
            map.put(lastSpoken, turn - 1); // overwrite for last turn after the lookup for this turn above
            lastSpoken = thisTurn;
            ++turn;
        }

        return lastSpoken;
    }
}
