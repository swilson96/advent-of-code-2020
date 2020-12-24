package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day23.Day23;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day23Test {
    private static final String EXAMPLE_INPUT = "389125467";

    @Test
    public void example() {
        var solver = new Day23();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(67384529);
    }

    @Test
    public void realThing() {
        var solver = new Day23();
        var result = solver.solvePartOne("459672813");
        assertThat(result).isEqualTo(68245739);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day23();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(149245887792L);
    }
}
