package uk.co.swilson.advent.day03;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.List;
import java.util.stream.Collectors;

public class Day03 implements Solver {

    private int checkSlope(String input, int slopeRight, int slopeDown) {
        var lines = input.lines().collect(Collectors.toList());
        int height = lines.size();
        int wrap = lines.get(0).length();
        int lineIndex = 0;
        int position = 0;
        int trees = 0;
        while (lineIndex < height) {
            if (lines.get(lineIndex).charAt(position) == '#') {
                ++trees;
            }
            position = (position + slopeRight) % wrap;
            lineIndex += slopeDown;
        }
        return trees;
    }

    public int solvePartOne(String input) {
        return checkSlope(input,3, 1);
    }

    public int solvePartTwo(String input) {
        List<Integer[]> slopes = Lists.newArrayList();
        slopes.add(new Integer[] { 1, 1});
        slopes.add(new Integer[] { 3, 1});
        slopes.add(new Integer[] { 5, 1});
        slopes.add(new Integer[] { 7, 1});
        slopes.add(new Integer[] { 1, 2});
        return slopes.stream()
                .map(slope -> checkSlope(input, slope[0], slope[1]))
                .reduce(1, (x, y) -> x * y);
    }
}
