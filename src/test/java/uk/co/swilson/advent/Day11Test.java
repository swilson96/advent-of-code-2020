package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day11.Day11;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day11Test {
    private static final String EXAMPLE_INPUT = "L.LL.LL.LL\n" +
            "LLLLLLL.LL\n" +
            "L.L.L..L..\n" +
            "LLLL.LL.LL\n" +
            "L.LL.LL.LL\n" +
            "L.LLLLL.LL\n" +
            "..L.L.....\n" +
            "LLLLLLLLLL\n" +
            "L.LLLLLL.L\n" +
            "L.LLLLL.LL";

    @Test
    public void testPartOneStageByStage() {
        var solver = new Day11();
        var original = solver.parseMap(EXAMPLE_INPUT);

        var step1 = solver.iterate(original);
        compareMaps(solver.parseMap("#.##.##.##\n" +
                "#######.##\n" +
                "#.#.#..#..\n" +
                "####.##.##\n" +
                "#.##.##.##\n" +
                "#.#####.##\n" +
                "..#.#.....\n" +
                "##########\n" +
                "#.######.#\n" +
                "#.#####.##"), step1);

        var step2 = solver.iterate(step1);
        compareMaps(solver.parseMap("#.LL.L#.##\n" +
                "#LLLLLL.L#\n" +
                "L.L.L..L..\n" +
                "#LLL.LL.L#\n" +
                "#.LL.LL.LL\n" +
                "#.LLLL#.##\n" +
                "..L.L.....\n" +
                "#LLLLLLLL#\n" +
                "#.LLLLLL.L\n" +
                "#.#LLLL.##"), step2);

        var step3 = solver.iterate(step2);
        compareMaps(solver.parseMap("#.##.L#.##\n" +
                "#L###LL.L#\n" +
                "L.#.#..#..\n" +
                "#L##.##.L#\n" +
                "#.##.LL.LL\n" +
                "#.###L#.##\n" +
                "..#.#.....\n" +
                "#L######L#\n" +
                "#.LL###L.L\n" +
                "#.#L###.##"), step3);

        var step4 = solver.iterate(step3);
        compareMaps(solver.parseMap("#.#L.L#.##\n" +
                "#LLL#LL.L#\n" +
                "L.L.L..#..\n" +
                "#LLL.##.L#\n" +
                "#.LL.LL.LL\n" +
                "#.LL#L#.##\n" +
                "..L.L.....\n" +
                "#L#LLLL#L#\n" +
                "#.LLLLLL.L\n" +
                "#.#L#L#.##"), step4);

        var step5 = solver.iterate(step4);
        compareMaps(solver.parseMap("#.#L.L#.##\n" +
                "#LLL#LL.L#\n" +
                "L.#.L..#..\n" +
                "#L##.##.L#\n" +
                "#.#L.LL.LL\n" +
                "#.#L#L#.##\n" +
                "..L.L.....\n" +
                "#L#L##L#L#\n" +
                "#.LLLLLL.L\n" +
                "#.#L#L#.##"), step5);

        var step6 = solver.iterate(step5);
        compareMaps(step5, step6);
    }

    private void compareMaps(String[][] expected, String[][] actual) {
        for (int x = 0; x < expected.length; ++x) {
            for (int y = 0; y < expected[x].length; ++y) {
                assertThat(expected[x][y]).isEqualTo(actual[x][y]);
            }
        }
    }

    @Test
    public void testCount() {
        var solver = new Day11();
        var map = solver.parseMap("#.#L.L#.##\n" +
                "#LLL#LL.L#\n" +
                "L.#.L..#..\n" +
                "#L##.##.L#\n" +
                "#.#L.LL.LL\n" +
                "#.#L#L#.##\n" +
                "..L.L.....\n" +
                "#L#L##L#L#\n" +
                "#.LLLLLL.L\n" +
                "#.#L#L#.##");
        var count = solver.countOccupiedSeats(map);
        assertThat(count).isEqualTo(37);
    }

    @Test
    public void testPartOne() {
        var solver = new Day11();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(37);
    }

    @Test
    public void testPartTwoStageByStage() {
        var solver = new Day11();
        var original = solver.parseMap(EXAMPLE_INPUT);

        var step1 = solver.iteratePartTwo(original);
        compareMaps(solver.parseMap("#.##.##.##\n" +
                "#######.##\n" +
                "#.#.#..#..\n" +
                "####.##.##\n" +
                "#.##.##.##\n" +
                "#.#####.##\n" +
                "..#.#.....\n" +
                "##########\n" +
                "#.######.#\n" +
                "#.#####.##"), step1);

        var step2 = solver.iteratePartTwo(step1);
        compareMaps(solver.parseMap("#.LL.LL.L#\n" +
                "#LLLLLL.LL\n" +
                "L.L.L..L..\n" +
                "LLLL.LL.LL\n" +
                "L.LL.LL.LL\n" +
                "L.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLL#\n" +
                "#.LLLLLL.L\n" +
                "#.LLLLL.L#"), step2);

        var step3 = solver.iteratePartTwo(step2);
        compareMaps(solver.parseMap("#.L#.##.L#\n" +
                "#L#####.LL\n" +
                "L.#.#..#..\n" +
                "##L#.##.##\n" +
                "#.##.#L.##\n" +
                "#.#####.#L\n" +
                "..#.#.....\n" +
                "LLL####LL#\n" +
                "#.L#####.L\n" +
                "#.L####.L#"), step3);

        var step4 = solver.iteratePartTwo(step3);
        compareMaps(solver.parseMap("#.L#.L#.L#\n" +
                "#LLLLLL.LL\n" +
                "L.L.L..#..\n" +
                "##LL.LL.L#\n" +
                "L.LL.LL.L#\n" +
                "#.LLLLL.LL\n" +
                "..L.L.....\n" +
                "LLLLLLLLL#\n" +
                "#.LLLLL#.L\n" +
                "#.L#LL#.L#"), step4);

        var step5 = solver.iteratePartTwo(step4);
        compareMaps(solver.parseMap("#.L#.L#.L#\n" +
                "#LLLLLL.LL\n" +
                "L.L.L..#..\n" +
                "##L#.#L.L#\n" +
                "L.L#.#L.L#\n" +
                "#.L####.LL\n" +
                "..#.#.....\n" +
                "LLL###LLL#\n" +
                "#.LLLLL#.L\n" +
                "#.L#LL#.L#"), step5);

        var step6 = solver.iteratePartTwo(step5);
        compareMaps(solver.parseMap("#.L#.L#.L#\n" +
                "#LLLLLL.LL\n" +
                "L.L.L..#..\n" +
                "##L#.#L.L#\n" +
                "L.L#.LL.L#\n" +
                "#.LLLL#.LL\n" +
                "..#.L.....\n" +
                "LLL###LLL#\n" +
                "#.LLLLL#.L\n" +
                "#.L#LL#.L#"), step6);

        compareMaps(solver.iteratePartTwo(step6), step6);
    }

    @Test
    public void testPartTwo() {
        var solver = new Day11();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(26);
    }
}
