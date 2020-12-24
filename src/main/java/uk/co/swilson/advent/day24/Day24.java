package uk.co.swilson.advent.day24;

import com.google.common.collect.Sets;
import uk.co.swilson.advent.Solver;
import uk.co.swilson.advent.day12.Vector;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day24 extends Solver {
    public long solvePartOne(String input) {
        return getBlackTiles(input).size();
    }

    public Vector mapToCanonical(String description) {
        Vector location = new Vector(0,0);
        Map<String, Integer> steps = new HashMap<>();
        var iterator = Arrays.stream(description.split("")).iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next.equals("s") || next.equals("n")) {
                next = next + iterator.next();
            }
            location = location.add(VECTORS.get(next));
        }
        return location;
    }

    private static final Vector NW = new Vector(-1, 2);
    private static final Vector NE = new Vector(1, 2);
    private static final Vector E = new Vector(2, 0);
    private static final Vector SE = new Vector(1, -2);
    private static final Vector SW = new Vector(-1, -2);
    private static final Vector W = new Vector(-2, 0);

    private final static Map<String, Vector> VECTORS = new HashMap<>() {{
        put("nw", NW);
        put("ne", NE);
        put("e", E);
        put("se", SE);
        put("sw", SW);
        put("w", W);
    }};

    public long solvePartTwo(String input) {
        var blackTiles = getBlackTiles(input);

        for (int day = 1; day <= 100; ++day) {
            var tilesByNumberOfBlackNeighbours= blackTiles.stream()
                    .flatMap(this::getNeighbours)
                    .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toSet())));
            var tilesWithTwoBlackNeighbours = tilesByNumberOfBlackNeighbours.computeIfAbsent(2L, k -> Collections.emptySet());
            var blackTilesWithOneBlackNeighbour = Sets.intersection(
                    blackTiles,
                    tilesByNumberOfBlackNeighbours.computeIfAbsent(1L, k -> Collections.emptySet()));
            blackTiles = Sets.union(tilesWithTwoBlackNeighbours, blackTilesWithOneBlackNeighbour);
            System.out.println("Day " + day + ": " + blackTiles.size() + " black tiles");
        }

        return blackTiles.size();
    }

    private Set<Vector> getBlackTiles(String input) {
        return input.lines()
                .map(this::mapToCanonical)
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 == 1) // Odd number of flips => black face upwards
                .map(e -> e.getKey())
                .collect(Collectors.toSet());
    }

    private Stream<Vector> getNeighbours(Vector tile) {
        return Stream.of(
                tile.add(NW),
                tile.add(NE),
                tile.add(E),
                tile.add(SE),
                tile.add(SW),
                tile.add(W)
        );
    }
}
