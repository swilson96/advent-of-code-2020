package uk.co.swilson.advent.day23;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 extends Solver {
    public long solvePartOne(String input) {
        var circle = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        var fullLength = circle.size();
        var currentCupIndex = 0;
        for (int turn = 1; turn <= 100; ++ turn) {
            int currentCupValue = circle.get(currentCupIndex);
            int r1 = circle.get((currentCupIndex + 1) % fullLength);
            int r2 = circle.get((currentCupIndex + 2) % fullLength);
            int r3 = circle.get((currentCupIndex + 3) % fullLength);
            circle.remove(circle.indexOf(r1));
            circle.remove(circle.indexOf(r2));
            circle.remove(circle.indexOf(r3));
            int destLabel = (currentCupValue + fullLength - 1) % fullLength;
            if (destLabel < 1) {
                destLabel += fullLength;
            }
            while (r1 == destLabel || r2 == destLabel || r3 == destLabel) {
                destLabel--;
                if (destLabel < 1) {
                    destLabel += fullLength;
                }
            }
            int destLabelIndex = circle.indexOf(destLabel);
            var newCircle = Lists.newArrayList(destLabel, r1, r2, r3);
            newCircle.addAll(elementsAfterIndex(circle, destLabelIndex));
            circle = newCircle;
            currentCupIndex = (circle.indexOf(currentCupValue) + 1) % fullLength;
        }
        var indexOfOne = circle.indexOf(1);
        var circleAfterOne = elementsAfterIndex(circle, indexOfOne);
        return Long.parseLong(circleAfterOne.stream().map(String::valueOf).collect(Collectors.joining("")));
    }

    private List<Integer> elementsAfterIndex(List<Integer> circle, int index) {
        List<Integer> reordered = Lists.newArrayList();
        if (index < circle.size() - 1) {
            reordered.addAll(circle.subList(index + 1, circle.size()));
        }
        if (index > 0) {
            reordered.addAll(circle.subList(0, index));
        }
        return reordered;
    }

    public long solvePartTwo(String input) {
        return 0;
    }
}
