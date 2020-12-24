package uk.co.swilson.advent.day24;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;
import uk.co.swilson.advent.day12.Vector;
import uk.co.swilson.advent.day17.GridVector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day24 extends Solver {
    public long solvePartOne(String input) {
        var locations = input.lines()
                .map(this::mapToCanonical)
                .collect(Collectors.toList());

        var counts = locations.stream().collect(Collectors.groupingBy(s -> 1000 * s.x + s.y, Collectors.counting())); // dirty hash

        return counts.entrySet()
                .stream()
                .filter(e -> e.getValue() % 2 == 1) // Odd number of flips => black face upwards
                .count();
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

    private final static Map<String, Vector> VECTORS = new HashMap<>() {{
        put("nw", new Vector(-1, 2));
        put("ne", new Vector(1, 2));
        put("e", new Vector(2, 0));
        put("se", new Vector(1, -2));
        put("sw", new Vector(-1, -2));
        put("w", new Vector(-2, 0));
    }};

    public long solvePartTwo(String input) {
        return 0;
    }
}
