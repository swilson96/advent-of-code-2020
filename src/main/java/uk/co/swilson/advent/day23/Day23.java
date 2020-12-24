package uk.co.swilson.advent.day23;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.*;
import java.util.stream.Collectors;

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
        return partTwoInner(input, 1000000, 10000000);
    }

    public long partTwoInner(String input, int totalSize, int turns) {
        Map<Integer, Cup> valMap = new HashMap<>();
        Cup cupOne = new Cup(1);
        valMap.put(1, cupOne);
        Cup prev = cupOne;
        for (int val = 2; val <= 9; ++val) {
            var cup = new Cup(val);
            cup.previousValue = prev;
            prev.nextValue = cup;
            valMap.put(val, cup);
            prev = cup;
        }
        Cup cupNine = prev;

        var currentCup = valMap.get(Integer.parseInt(input.substring(0, 1)));
        prev = currentCup;
        for (int i = 1; i < 9; ++i) {
            var nextVal = Integer.parseInt(input.substring(i, i+1));
            var next = valMap.get(nextVal);
            prev.next = next;
            next.previous = prev;
            prev = next;
        }

        if (totalSize == 9) {
            prev.next = currentCup;
            currentCup.previous = prev;
            cupOne.previousValue = cupNine;
            cupNine.nextValue = cupOne;
        } else {
            // Do the first by hand
            var prevCup = new Cup(10);
            prevCup.previousValue = cupNine;
            prevCup.previous = prev;
            cupNine.nextValue = prevCup;
            prev.next = prevCup;

            for (int i = 11; i <= totalSize; ++i) {
                var nextCup = new Cup(i);
                nextCup.previous = prevCup;
                nextCup.previousValue = prevCup;
                prevCup.nextValue = nextCup;
                prevCup.next = nextCup;
                prevCup = nextCup;
            }

            prevCup.next = currentCup;
            prevCup.nextValue = cupOne;
            cupOne.previousValue = prevCup;
            currentCup.previous = prevCup;
        }

        for (int turn = 1; turn <= turns; ++turn) {
            // Identify the three cups
            Cup r1 = currentCup.next;
            Cup r2 = r1.next;
            Cup r3 = r2.next;
            // Identify the destination
            Cup destCup = currentCup.previousValue;
            while (destCup.value == r1.value || destCup.value == r2.value || destCup.value == r3.value) {
                destCup = destCup.previousValue;
            }
            Cup cupAfterDest = destCup.next;
            Cup firstOfRemainder = r3.next;
            // Insert
            destCup.next = r1;
            r1.previous = destCup;
            r3.next = cupAfterDest;
            cupAfterDest.previous = r3;
            // Close the hole
            currentCup.next = firstOfRemainder;
            firstOfRemainder.previous = currentCup;
            // select the next cup
            currentCup = firstOfRemainder;
        }

        Cup starOne = cupOne.next;
        Cup starTwo = starOne.next;
        return (long) starOne.value * (long) starTwo.value;
    }

    private static class Cup {
        public final int value;
        public Cup next;
        public Cup previous;
        public Cup nextValue;
        public Cup previousValue;

        public Cup(int value) {
            this.value = value;
        }
    }
}
