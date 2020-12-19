package uk.co.swilson.advent.day17;

import uk.co.swilson.advent.Solver;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day17 implements Solver {
    public long solvePartOne(String input) {
        Set<ConwayCube> initialCubes = parseInput(input);
        PocketDimension dimension = new PocketDimension(initialCubes);

        var cycle = 1;
        while (cycle <= 6) {
            dimension = iterate(dimension);
            ++cycle;
        }

        return dimension.numActive();
    }

    private PocketDimension iterate(PocketDimension prev) {
        Set<ConwayCube> cubes = new HashSet<>();
        for (int x = -1; x <= prev.max.x + 1; ++x) {
            for (int y = -1; y <= prev.max.y + 1; ++y) {
                for (int z = -1; z <= prev.max.z + 1; ++z) {
                    var activeNeighbours = neighboursIncludingSelf(x, y, z)
                            .filter(v -> prev.isActive(v.x, v.y, v.z))
                            .count();
                    var wasActive = prev.isActive(x, y, z);
                    if (wasActive) {
                        --activeNeighbours;
                    }
                    var isActive = false;
                    if (wasActive && (activeNeighbours == 2 || activeNeighbours == 3)) {
                        isActive = true;
                    }
                    if (!wasActive && activeNeighbours == 3) {
                        isActive = true;
                    }
                    cubes.add(new ConwayCube(x, y, z, isActive));
                }
            }
        }
        return new PocketDimension(cubes);
    }

    private Stream<GridVector> neighboursIncludingSelf(int x, int y, int z) {
        return Stream.of(x - 1, x, x + 1)
                .flatMap(xin -> Stream.of(new Point(xin, y - 1), new Point(xin, y), new Point(xin, y + 1)))
                .flatMap(p -> Stream.of(new GridVector(p.x, p.y, z - 1), new GridVector(p.x, p.y, z), new GridVector(p.x, p.y, z + 1)));
    }

    private class Point {
        private final int x;
        private final int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private Set<ConwayCube> parseInput(String input) {
        final var z = 0;
        var y = 0;
        Set<ConwayCube> result = new HashSet<>();
        for (var line : input.lines().collect(Collectors.toList())) {
            var x = 0;
            for (var c : line.split("")) {
                result.add(new ConwayCube(x, y, z, "#".equals(c)));
                ++x;
            }
            ++y;
        }
        return result;
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}
