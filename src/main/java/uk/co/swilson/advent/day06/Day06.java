package uk.co.swilson.advent.day06;

import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class Day06 extends Solver {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public long solvePartOne(String input) {
        return countQuestionsMatching(input, this::countUnionQuestionsInGroup);
    }

    public long solvePartTwo(String input) {
        return countQuestionsMatching(input, this::countIntersectionQuestionsInGroup);
    }

    public int countQuestionsMatching(String input, ToIntFunction<String> countQuestionMatchesForGroup) {
        var groups = input.split("\r?\n\r?\n");
        return Arrays.stream(groups)
                .mapToInt(countQuestionMatchesForGroup)
                .sum();
    }

    public int countUnionQuestionsInGroup(String group) {
        int count = 0;
        for (String question : ALPHABET.split("")) {
            if (group.lines().anyMatch(member -> member.contains(question))) {
                ++count;
            }
        }
        return count;
    }

    public int countIntersectionQuestionsInGroup(String group) {
        int count = 0;
        for (String question : ALPHABET.split("")) {
            if (group.lines().allMatch(member -> member.contains(question))) {
                ++count;
            }
        }
        return count;
    }
}
