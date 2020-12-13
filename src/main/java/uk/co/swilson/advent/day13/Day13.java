package uk.co.swilson.advent.day13;

import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day13 implements Solver {
    public long solvePartOne(String input) {
        var lines = input.split("\r?\n");
        var departureTime = Integer.parseInt(lines[0]);
        var schedule = Arrays.stream(lines[1].split(","))
                .filter(s -> !s.equals("x"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        var bestId = 0;
        var bestTime = Integer.MAX_VALUE;
        for (var id : schedule) {
            var time = timeToNextDeparture(departureTime, id);
            if (time < bestTime) {
                bestTime = time;
                bestId = id;
            }
        }

        return bestId * bestTime;
    }

    private int timeToNextDeparture(int departureTime, int id) {
        var timeSinceLastDeparture = departureTime % id;
        if (timeSinceLastDeparture < 0) {
            timeSinceLastDeparture = timeSinceLastDeparture + id;
        }
        return id - timeSinceLastDeparture;
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}
