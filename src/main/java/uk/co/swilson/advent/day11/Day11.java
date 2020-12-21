package uk.co.swilson.advent.day11;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;

public class Day11 extends Solver {
    private boolean didChange = true;

    public long solvePartOne(String input) {
        String[][] map = parseMap(input);

        do {
            map = iterate(map);
        } while (didChange);

        return countOccupiedSeats(map);
    }

    public String[][] parseMap(String input) {
        return input.lines().map(l -> l.split("")).toArray(String[][]::new);
    }

    public String[][] iterate(String[][] original) {
        didChange = false;
        List<String[]> next = Lists.newArrayList();
        int height = original.length;
        for (int x = 0; x < height; ++x) {
            List<String> row = Lists.newArrayList();
            int rowLength = original[x].length;
            for (int y = 0; y < rowLength; ++y) {
                var originalSeat = original[x][y];
                if (originalSeat.equals(".")) {
                    row.add(".");
                } else {
                    var occupiedAdjacentCount = 0;

                    occupiedAdjacentCount += (x > 0 && y > 0 && original[x - 1][y - 1].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (x > 0 && original[x - 1][y].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (x > 0 && y < rowLength - 1 && original[x - 1][y + 1].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (y < rowLength - 1 && original[x][y + 1].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (x < height - 1 && y < rowLength - 1 && original[x + 1][y + 1].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (x < height - 1 && original[x + 1][y].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (x < height - 1 && y > 0 && original[x + 1][y - 1].equals("#")) ? 1 : 0;
                    occupiedAdjacentCount += (y > 0 && original[x][y - 1].equals("#")) ? 1 : 0;

                    var nextState = originalSeat.equals("L")
                            ? occupiedAdjacentCount == 0 ? "#" : "L"
                            : occupiedAdjacentCount >= 4 ? "L" : "#";

                    didChange |= !nextState.equals(originalSeat);

                    row.add(nextState);
                }
            }
            next.add(row.toArray(String[]::new));
        }
        return next.toArray(String[][]::new);
    }

    public long countOccupiedSeats(String[][] map) {
        return Arrays.stream(map)
                .flatMap(Arrays::stream)
                .filter(seat -> seat.equals("#"))
                .count();
    }

    public long solvePartTwo(String input) {
        String[][] map = parseMap(input);

        do {
            map = iteratePartTwo(map);
        } while (didChange);

        return countOccupiedSeats(map);
    }

    public String[][] iteratePartTwo(String[][] original) {
        didChange = false;
        List<String[]> next = Lists.newArrayList();
        int height = original.length;
        for (int x = 0; x < height; ++x) {
            List<String> row = Lists.newArrayList();
            int rowLength = original[x].length;
            for (int y = 0; y < rowLength; ++y) {
                var originalSeat = original[x][y];
                if (originalSeat.equals(".")) {
                    row.add(".");
                } else {
                    var occupiedVisibleCount = 0;

                    occupiedVisibleCount += canSeeSeat(x, y, -1, -1, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, -1, 0, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, -1, 1, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, 0, 1, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, 1, 1, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, 1, 0, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, 1, -1, original) ? 1 : 0;
                    occupiedVisibleCount += canSeeSeat(x, y, 0, -1, original) ? 1 : 0;

                    var nextState = originalSeat.equals("L")
                            ? occupiedVisibleCount == 0 ? "#" : "L"
                            : occupiedVisibleCount >= 5 ? "L" : "#";

                    didChange |= !nextState.equals(originalSeat);

                    row.add(nextState);
                }
            }
            next.add(row.toArray(String[]::new));
        }
        return next.toArray(String[][]::new);
    }

    private boolean canSeeSeat(int x, int y, int xDir, int yDir, String[][] map) {
        int checkX = x + xDir;
        int checkY = y + yDir;
        while (inRange(checkX, checkY, map)) {
            var canSee = map[checkX][checkY];
            if (canSee.equals("L")) {
                return false;
            }
            if (canSee.equals("#")) {
                return true;
            }
            checkX += xDir;
            checkY += yDir;
        }
        return false;
    }

    private boolean inRange(int x, int y, String[][] map) {
        return x >= 0 && y >= 0 && x < map.length && y < map[x].length;
    }
}
