package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day21.Day21;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day21Test {
    private static final String EXAMPLE_INPUT = "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)\n" +
            "trh fvjkl sbzzf mxmxvkd (contains dairy)\n" +
            "sqjhc fvjkl (contains soy)\n" +
            "sqjhc mxmxvkd sbzzf (contains fish)";

    @Test
    public void example() {
        var solver = new Day21();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day21();
        var result = solver.solvePartTwoToString(EXAMPLE_INPUT);
        assertThat(result).isEqualTo("mxmxvkd,sqjhc,fvjkl");
    }
}
