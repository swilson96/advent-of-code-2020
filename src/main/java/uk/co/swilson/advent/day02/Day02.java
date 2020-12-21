package uk.co.swilson.advent.day02;

import uk.co.swilson.advent.Solver;

public class Day02 extends Solver {
    public long solvePartOne(String input) {
        return (int) input.lines()
                .map(s -> s.split(": "))
                .filter(entry -> {
                    var ruleBits = entry[0].split(" ");
                    var testChar = ruleBits[1];
                    var range = ruleBits[0].split("-");
                    var occurrences = entry[1].chars().filter(c -> c == testChar.charAt(0)).count();
                    return Long.parseLong(range[0]) <= occurrences && occurrences <= Long.parseLong(range[1]);
                })
                .count();
    }

    public long solvePartTwo(String input) {
        return (int) input.lines()
                .map(s -> s.split(": "))
                .filter(entry -> {
                    var ruleBits = entry[0].split(" ");
                    var testChar = ruleBits[1].charAt(0);
                    var range = ruleBits[0].split("-");
                    var password = entry[1];
                    var firstPositionMatches = password.charAt(Integer.parseInt(range[0]) - 1) == testChar;
                    var secondPositionMatches = password.charAt(Integer.parseInt(range[1]) - 1) == testChar;
                    return (firstPositionMatches && !secondPositionMatches) || (!firstPositionMatches && secondPositionMatches);
                })
                .count();
    }
}
