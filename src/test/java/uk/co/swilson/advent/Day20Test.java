package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day20.Day20;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day20Test {
    private static final String EXAMPLE_INPUT = "Tile 2311:\n" +
            "..##.#..#.\n" +
            "##..#.....\n" +
            "#...##..#.\n" +
            "####.#...#\n" +
            "##.##.###.\n" +
            "##...#.###\n" +
            ".#.#.#..##\n" +
            "..#....#..\n" +
            "###...#.#.\n" +
            "..###..###\n" +
            "\n" +
            "Tile 1951:\n" +
            "#.##...##.\n" +
            "#.####...#\n" +
            ".....#..##\n" +
            "#...######\n" +
            ".##.#....#\n" +
            ".###.#####\n" +
            "###.##.##.\n" +
            ".###....#.\n" +
            "..#.#..#.#\n" +
            "#...##.#..\n" +
            "\n" +
            "Tile 1171:\n" +
            "####...##.\n" +
            "#..##.#..#\n" +
            "##.#..#.#.\n" +
            ".###.####.\n" +
            "..###.####\n" +
            ".##....##.\n" +
            ".#...####.\n" +
            "#.##.####.\n" +
            "####..#...\n" +
            ".....##...\n" +
            "\n" +
            "Tile 1427:\n" +
            "###.##.#..\n" +
            ".#..#.##..\n" +
            ".#.##.#..#\n" +
            "#.#.#.##.#\n" +
            "....#...##\n" +
            "...##..##.\n" +
            "...#.#####\n" +
            ".#.####.#.\n" +
            "..#..###.#\n" +
            "..##.#..#.\n" +
            "\n" +
            "Tile 1489:\n" +
            "##.#.#....\n" +
            "..##...#..\n" +
            ".##..##...\n" +
            "..#...#...\n" +
            "#####...#.\n" +
            "#..#.#.#.#\n" +
            "...#.#.#..\n" +
            "##.#...##.\n" +
            "..##.##.##\n" +
            "###.##.#..\n" +
            "\n" +
            "Tile 2473:\n" +
            "#....####.\n" +
            "#..#.##...\n" +
            "#.##..#...\n" +
            "######.#.#\n" +
            ".#...#.#.#\n" +
            ".#########\n" +
            ".###.#..#.\n" +
            "########.#\n" +
            "##...##.#.\n" +
            "..###.#.#.\n" +
            "\n" +
            "Tile 2971:\n" +
            "..#.#....#\n" +
            "#...###...\n" +
            "#.#.###...\n" +
            "##.##..#..\n" +
            ".#####..##\n" +
            ".#..####.#\n" +
            "#..#.#..#.\n" +
            "..####.###\n" +
            "..#.#.###.\n" +
            "...#.#.#.#\n" +
            "\n" +
            "Tile 2729:\n" +
            "...#.#.#.#\n" +
            "####.#....\n" +
            "..#.#.....\n" +
            "....#..#.#\n" +
            ".##..##.#.\n" +
            ".#.####...\n" +
            "####.#.#..\n" +
            "##.####...\n" +
            "##..#.##..\n" +
            "#.##...##.\n" +
            "\n" +
            "Tile 3079:\n" +
            "#.#.#####.\n" +
            ".#..######\n" +
            "..#.......\n" +
            "######....\n" +
            "####.#..#.\n" +
            ".#...#.##.\n" +
            "#.#####.##\n" +
            "..#.###...\n" +
            "..#.......\n" +
            "..#.###...";

    @Test
    public void example() {
        var solver = new Day20();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(20899048083289L);
    }

    @Test
    public void testSingleTile() {
        var tile = new Day20.Tile("Tile 2311:\n" +
                "..##.#..#.\n" +
                "##..#.....\n" +
                "#...##..#.\n" +
                "####.#...#\n" +
                "##.##.###.\n" +
                "##...#.###\n" +
                ".#.#.#..##\n" +
                "..#....#..\n" +
                "###...#.#.\n" +
                "..###..###");

        assertThat(tile.id).isEqualTo(2311);

        // Top: 11010010 / 100101100
        assertThat(tile.edgesClockwiseFromTop[0].signature).isEqualTo(210);
        assertThat(tile.edgesClockwiseFromTop[0].flippedSignature).isEqualTo(300);

        // Right: 1011001 / 1001101000
        assertThat(tile.edgesClockwiseFromTop[1].signature).isEqualTo(89);
        assertThat(tile.edgesClockwiseFromTop[1].flippedSignature).isEqualTo(616);

        // Bottom: 1110011100 / 11100111
        assertThat(tile.edgesClockwiseFromTop[2].signature).isEqualTo(924);
        assertThat(tile.edgesClockwiseFromTop[2].flippedSignature).isEqualTo(231);

        // Left: 100111110 / 111110010
        assertThat(tile.edgesClockwiseFromTop[3].signature).isEqualTo(318);
        assertThat(tile.edgesClockwiseFromTop[3].flippedSignature).isEqualTo(498);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day20();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(0);
    }
}
