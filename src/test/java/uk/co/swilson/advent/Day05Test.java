package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day05.Day05;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day05Test {
    @Test
    public void example1() {
        var solver = new Day05();
        var result = solver.seatID("FBFBBFFRLR");
        assertThat(result).isEqualTo(357);
    }

    @Test
    public void example2() {
        var solver = new Day05();
        var result = solver.seatID("BFFFBBFRRR");
        assertThat(result).isEqualTo(567);
    }

    @Test
    public void example3() {
        var solver = new Day05();
        var result = solver.seatID("FFFBBBFRRR");
        assertThat(result).isEqualTo(119);
    }

    @Test
    public void example4() {
        var solver = new Day05();
        var result = solver.seatID("BBFFBBFRLL");
        assertThat(result).isEqualTo(820);
    }
}
