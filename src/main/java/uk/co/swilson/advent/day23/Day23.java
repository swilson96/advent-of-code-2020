package uk.co.swilson.advent.day23;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day23 extends Solver {
    public long solvePartOne(String input) {
        var circle = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        var result = crabShuffle(circle, 100);
        var indexOfOne = result.indexOf(1);
        var circleAfterOne = elementsAfterIndex(result, indexOfOne);
        return Long.parseLong(circleAfterOne.stream().map(String::valueOf).collect(Collectors.joining("")));
    }

    private List<Integer> crabShuffle(List<Integer> cups, int turns) {
        var circle = new LinkedList<>(cups);
        var fullLength = circle.size();
        var currentCupIndex = 0;
        for (int turn = 1; turn <= turns; ++ turn) {
            int currentCupValue = circle.get(currentCupIndex);
            int removalsWhichLooped = currentCupIndex < fullLength - 3
                    ? 0
                    : 4 + currentCupIndex - fullLength;
            int r1 = currentCupIndex == fullLength - 1
                    ? circle.remove(0)
                    : circle.remove(currentCupIndex + 1);
            int r2 = currentCupIndex >= fullLength - 2
                    ? circle.remove(0)
                    : circle.remove(currentCupIndex + 1);
            int r3 = currentCupIndex >= fullLength - 3
                    ? circle.remove(0)
                    : circle.remove(currentCupIndex + 1);

            int destLabel = currentCupValue - 1;
            if (destLabel < 1) {
                destLabel += fullLength;
            }
            while (r1 == destLabel || r2 == destLabel || r3 == destLabel) {
                destLabel = destLabel == 1 ? fullLength : destLabel - 1;
            }
            int destIndex = circle.indexOf(destLabel);
            circle.add(destIndex + 1, r3);
            circle.add(destIndex + 1, r2);
            circle.add(destIndex + 1, r1);
            if (turn % 10000 == 0) {
                System.out.printf("Turn %d: %s currentIdx=%d currentVal=%s destIdx=%d\n", turn,
                        circle.stream().limit(20).map(String::valueOf).collect(Collectors.joining(",")),
                        currentCupIndex, currentCupValue, destIndex);
            }
            int currentCupIndexAfterShuffle = currentCupIndex - removalsWhichLooped;
            if (destIndex < currentCupIndexAfterShuffle) {
                currentCupIndexAfterShuffle += 3;
            }
            currentCupIndex = (currentCupIndexAfterShuffle + 1) % fullLength;
        }
        return circle;
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
        var allCups = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        allCups.addAll(IntStream.rangeClosed(allCups.size() + 1, 1000000).boxed().collect(Collectors.toList()));
        var result = crabShuffle(allCups, 10000000); //10000000
        var indexOfOne = result.indexOf(1);
        long starOne = result.get((indexOfOne + 1) % 1000000);
        long starTwo = result.get((indexOfOne + 2) % 1000000);
        return starOne * starTwo;
    }
}
