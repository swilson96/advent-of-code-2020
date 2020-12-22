package uk.co.swilson.advent.day22;

import uk.co.swilson.advent.Solver;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day22 extends Solver {
    public long solvePartOne(String input) {
        var playerInputs = input.split("\\r?\\n\\r?\\n");
        Queue<Integer> p1 = playerInputs[0].lines().skip(1).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        Queue<Integer> p2 = playerInputs[1].lines().skip(1).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));

        while (!p1.isEmpty() && !p2.isEmpty()) {
            var p1card = p1.poll();
            var p2card = p2.poll();
            if (p1card > p2card) {
                p1.add(p1card);
                p1.add(p2card);
            } else {
                p2.add(p2card);
                p2.add(p1card);
            }
        }

        var winningHand = p2.isEmpty() ? p1 : p2;

        return scoreHand(winningHand);
    }

    private long scoreHand(Queue<Integer> hand) {
        var multiplier = hand.size();
        var acc = 0;
        while (!hand.isEmpty()) {
            acc += multiplier-- * hand.poll();
        }
        return acc;
    }

    public long solvePartTwo(String input) {

        var playerInputs = input.split("\\r?\\n\\r?\\n");
        LinkedList<Integer> p1 = playerInputs[0].lines().skip(1).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));
        LinkedList<Integer> p2 = playerInputs[1].lines().skip(1).map(Integer::parseInt).collect(Collectors.toCollection(LinkedList::new));

        int winner = playRecursiveCombat(p1, p2);

        var winningHand = winner == 1 ? p1 : p2;

        return scoreHand(winningHand);
    }

    private int playRecursiveCombat(LinkedList<Integer> p1, LinkedList<Integer> p2) {
        var previousRounds = new HashSet<String>();
        while (!p1.isEmpty() && !p2.isEmpty()) {

            var roundHash = "p1:"
                    + p1.stream().map(String::valueOf).collect(Collectors.joining(","))
                    + ";p2:"
                    + p2.stream().map(String::valueOf).collect(Collectors.joining(","));
            if (previousRounds.contains(roundHash)) {
                return 1;
            }
            previousRounds.add(roundHash);

            var p1card = p1.poll();
            var p2card = p2.poll();

            var winner = 0;
            if (p1.size() < p1card || p2.size() < p2card) {
                winner = p1card > p2card ? 1 : 2;
            } else {
                winner = playRecursiveCombat(new LinkedList<>(p1.subList(0, p1card)), new LinkedList<>(p2.subList(0, p2card)));
            }

            if (winner == 1) {
                p1.add(p1card);
                p1.add(p2card);
            } else {
                p2.add(p2card);
                p2.add(p1card);
            }
        }
        return p2.isEmpty() ? 1 : 2;
    }
}
