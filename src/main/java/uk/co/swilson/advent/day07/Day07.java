package uk.co.swilson.advent.day07;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 implements Solver {
    private static final String MY_BAG = "shiny gold";

    public int solvePartOne(String input) {
        Map<String, List<String>> toParents = new HashMap<String, List<String>>();
        input.lines().map(Rule::new)
                .forEach(rule -> {
                    for (var child : rule.children) {
                        toParents.putIfAbsent(child, Lists.newArrayList());
                        toParents.get(child).add(rule.parent);
                    }
                });

        Set<String> possibleParents = new HashSet<String>();
        possibleParents.addAll(toParents.get(MY_BAG));

        var increased = true;
        while (increased) {
            var originalSize = possibleParents.size();
            var newParents = possibleParents.stream()
                    .map(p -> toParents.getOrDefault(p, Lists.newArrayList()))
                    .flatMap(ps -> ps.stream())
                    .collect(Collectors.toSet());
            possibleParents.addAll(newParents);
            increased = possibleParents.size() > originalSize;
        }

        return possibleParents.size();
    }

    public int solvePartTwo(String input) {
        return 0;
    }
}
