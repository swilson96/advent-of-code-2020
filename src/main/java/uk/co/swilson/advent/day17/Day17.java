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
                    var activeNeighbours = neighboursIncludingSelfInThreeDimensions(x, y, z)
                            .filter(v -> prev.isActive(v.x, v.y, v.z, 0))
                            .count();
                    var wasActive = prev.isActive(x, y, z, 0);
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
                    cubes.add(new ConwayCube(x, y, z, 0, isActive));
                }
            }
        }
        return new PocketDimension(cubes);
    }

    private Stream<GridVector> neighboursIncludingSelfInThreeDimensions(int x, int y, int z) {
        return Stream.of(x - 1, x, x + 1)
                .flatMap(xin -> Stream.of(new TwoPoint(xin, y - 1), new TwoPoint(xin, y), new TwoPoint(xin, y + 1)))
                .flatMap(p -> Stream.of(new GridVector(p.x, p.y, z - 1, 0), new GridVector(p.x, p.y, z, 0), new GridVector(p.x, p.y, z + 1, 0)));
    }

    private Stream<GridVector> neighboursIncludingSelfInFourDimensions(int x, int y, int z, int w) {
        return Stream.of(x - 1, x, x + 1)
                .flatMap(xin -> Stream.of(new TwoPoint(xin, y - 1), new TwoPoint(xin, y), new TwoPoint(xin, y + 1)))
                .flatMap(p -> Stream.of(new ThreePoint(p.x, p.y, z - 1), new ThreePoint(p.x, p.y, z), new ThreePoint(p.x, p.y, z + 1)))
                .flatMap(p -> Stream.of(new GridVector(p.x, p.y, p.z, w - 1), new GridVector(p.x, p.y, p.z, w), new GridVector(p.x, p.y, p.z, w + 1)));
    }

    private class TwoPoint {
        private final int x;
        private final int y;
        public TwoPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private class ThreePoint {
        private final int x;
        private final int y;
        private final int z;
        public ThreePoint(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private Set<ConwayCube> parseInput(String input) {
        final var z = 0;
        final var w = 0;
        var y = 0;
        Set<ConwayCube> result = new HashSet<>();
        for (var line : input.lines().collect(Collectors.toList())) {
            var x = 0;
            for (var c : line.split("")) {
                result.add(new ConwayCube(x, y, z, w, "#".equals(c)));
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
