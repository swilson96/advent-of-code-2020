package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day25.Day25;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day25Test {
    private static final String EXAMPLE_INPUT = "5764801\n17807724";

    @Test
    public void decryptCardExample() {
        var solver = new Day25();
        var result = solver.decryptKey(5764801);
        assertThat(result).isEqualTo(8);
    }

    @Test
    public void decryptDoorExample() {
        var solver = new Day25();
        var result = solver.decryptKey(17807724);
        assertThat(result).isEqualTo(11);
    }

    @Test
    public void partOneExample() {
        var solver = new Day25();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(14897079);
    }
}
