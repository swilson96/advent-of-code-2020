package uk.co.swilson.advent.day07;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 implements Solver {
    private static final String MY_BAG = "shiny gold";

    public long solvePartOne(String input) {
        Map<String, List<String>> possibleParentsOf = new HashMap<>();
        input.lines().map(Rule::new)
                .forEach(rule -> {
                    for (var child : rule.children) {
                        possibleParentsOf.putIfAbsent(child.name, Lists.newArrayList());
                        possibleParentsOf.get(child.name).add(rule.parent);
                    }
                });

        Set<String> possibleParents = new HashSet<>();
        possibleParents.addAll(possibleParentsOf.get(MY_BAG));

        var increased = true;
        while (increased) {
            var originalSize = possibleParents.size();
            var newParents = possibleParents.stream()
                    .map(p -> possibleParentsOf.getOrDefault(p, Lists.newArrayList()))
                    .flatMap(ps -> ps.stream())
                    .collect(Collectors.toSet());
            possibleParents.addAll(newParents);
            increased = possibleParents.size() > originalSize;
        }

        return possibleParents.size();
    }

    public long solvePartTwo(String input) {
        var iterator = new BagIterator(input);
        return iterator.countBagsInSingleBag(MY_BAG);
    }
}
