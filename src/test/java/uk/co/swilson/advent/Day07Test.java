package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day07.BagIterator;
import uk.co.swilson.advent.day07.Day07;
import uk.co.swilson.advent.day07.Rule;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day07Test {
    private static final String EXAMPLE_INPUT = "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.\n" +
            "bright white bags contain 1 shiny gold bag.\n" +
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.\n" +
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.\n" +
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.\n" +
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
            "faded blue bags contain no other bags.\n" +
            "dotted black bags contain no other bags.";

    public static final String SECOND_EXAMPLE = "shiny gold bags contain 2 dark red bags.\n" +
            "dark red bags contain 2 dark orange bags.\n" +
            "dark orange bags contain 2 dark yellow bags.\n" +
            "dark yellow bags contain 2 dark green bags.\n" +
            "dark green bags contain 2 dark blue bags.\n" +
            "dark blue bags contain 2 dark violet bags.\n" +
            "dark violet bags contain no other bags.";

    @Test
    public void parseRule() {
        var rule = new Rule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");

        assertThat(rule.parent).isEqualTo("dark orange");
        assertThat(rule.children.size()).isEqualTo(2);
        assertThat(rule.children.get(0).name).isEqualTo("bright white");
        assertThat(rule.children.get(1).name).isEqualTo("muted yellow");
    }

    @Test
    public void parseRuleForNoBags() {
        var rule = new Rule("faded blue bags contain no other bags.");
        assertThat(rule.parent).isEqualTo("faded blue");
        assertThat(rule.children.size()).isEqualTo(0);
    }

    @Test
    public void example() {
        var solver = new Day07();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day07();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(32);
    }

    @Test
    public void partTwoExampleTwo() {
        var solver = new Day07();
        var result = solver.solvePartTwo(SECOND_EXAMPLE);
        assertThat(result).isEqualTo(126);
    }

    @Test
    public void partTwoExampleTwoSingleIteration() {
        var result = new BagIterator(SECOND_EXAMPLE);
        assertThat(result.countBagsInSingleBag("dark violet")).isEqualTo(0);
    }

    @Test
    public void partTwoExampleTwoDoubleIteration() {
        var result = new BagIterator(SECOND_EXAMPLE);
        assertThat(result.countBagsInSingleBag("dark blue")).isEqualTo(2);
    }
}
