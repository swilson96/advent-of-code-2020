package uk.co.swilson.advent.day05;

import uk.co.swilson.advent.Solver;

import java.util.stream.Collectors;

public class Day05 implements Solver {
    public int solvePartOne(String input) {
        return input.lines().mapToInt(this::seatID).max().orElse(-1);
    }

    public int seatID(String input) {
        var rowBits = stringToBinary(input.substring(0, 7), 'F', 'B');
        var colBits = stringToBinary(input.substring(7, 10), 'L', 'R');
        var row = Integer.parseInt(rowBits, 2);
        var col = Integer.parseInt(colBits, 2);
        return row * 8 + col;
    }

    private String stringToBinary(String input, char zero, char one) {
        return input.chars().mapToObj(c -> c == zero ? "0" : "1").collect(Collectors.joining());
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
