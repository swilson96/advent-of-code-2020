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
        var schedule = input.split("\r?\n")[1].split(",");

        var factorSoFar = Long.parseLong(schedule[0]);
        var solution = factorSoFar;

        for (int i = 1; i < schedule.length; ++i) {
            var entry = schedule[i];
            if (entry.equals("x")) {
                continue;
            }
            var busId = Long.parseLong(entry);
            solution = find(solution, factorSoFar, busId, i);
            factorSoFar = lcm(factorSoFar, busId);
        }

        return solution;
    }

    // a,x..x,b with "offset - 1" xs, i.e. b has to be offset minutes later than a
    private long find(long solution, long factor, long b, long offset) {
        while ((solution + offset) % b != 0) {
            solution += factor;
        }
        return solution;
    }

    private long lcm(long a, long b) {
        var larger = Math.max(a, b);
        var smaller = Math.min(a, b);
        var lcm = larger;
        while (lcm % smaller != 0) {
            lcm += larger;
        }
        return lcm;
    }
}
