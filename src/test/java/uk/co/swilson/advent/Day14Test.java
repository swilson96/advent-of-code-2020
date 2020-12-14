package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day14.Day14;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day14Test {
    @Test
    public void example() {
        var solver = new Day14();
        var result = solver.solvePartOne("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\n" +
                "mem[8] = 11\n" +
                "mem[7] = 101\n" +
                "mem[8] = 0");
        assertThat(result).isEqualTo(165);
    }

    @Test
    public void largeBit() {
        var solver = new Day14();
        var result = solver.solvePartOne("mask = 1XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                "mem[8] = 0");
        assertThat(result).isEqualTo(1L << 35);
        assertThat(result).isEqualTo(34359738368L);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day14();
        var result = solver.solvePartTwo("mask = 000000000000000000000000000000X1001X\n" +
                "mem[42] = 100\n" +
                "mask = 00000000000000000000000000000000X0XX\n" +
                "mem[26] = 1");
        assertThat(result).isEqualTo(208);
    }

    @Test
    public void largeBitPartTwo() {
        var solver = new Day14();
        var result = solver.solvePartTwo("mask = 100000000000000000000000000000000000\n" +
                "mem[8] = 0");
        assertThat(result).isEqualTo(0);
    }
}
