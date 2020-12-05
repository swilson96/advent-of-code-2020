package uk.co.swilson.advent.day05;

import uk.co.swilson.advent.Solver;

import java.util.stream.Collectors;

public class Day05 implements Solver {
    public int solvePartOne(String input) {
        return input.lines().mapToInt(this::seatID).max().orElse(-1);
    }

    public int seatID(String input) {
        var row = stringToBinary(input.substring(0, 7), 'F', 'B');
        var col = stringToBinary(input.substring(7, 10), 'L', 'R');
        return row * 8 + col;
    }

    private int stringToBinary(String input, char zero, char one) {
        return Integer.parseInt(
                input.chars()
                    .mapToObj(c -> c == zero ? "0" : "1")
                    .collect(Collectors.joining()),
                2);
    }

    public int solvePartTwo(String input) {
        var allKnownSeats = input.lines().map(this::seatID).sorted().collect(Collectors.toList());
        int prev = -3;
        for (int seatID : allKnownSeats) {
            if (seatID - prev == 2) {
                return seatID - 1;
            }
            prev = seatID;
        }
        return -1;
    }
}
