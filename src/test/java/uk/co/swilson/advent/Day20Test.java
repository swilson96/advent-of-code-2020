package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day20.Day20;

import java.io.InputStream;

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
    public void partOneRealThing() throws Exception {
        var solver = new Day20();
        InputStream is = Day20.class.getClassLoader().getResourceAsStream("input20.txt");
        var input = new String(is.readAllBytes());
        var result = solver.solvePartOne(input);
        assertThat(result).isEqualTo(27798062994017L);
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

        assertThat(tile.payload[0][0]).isEqualTo("#");
        assertThat(tile.payload[0][1]).isEqualTo(".");
        assertThat(tile.payload[0][2]).isEqualTo(".");
        assertThat(tile.payload[0][3]).isEqualTo("#");
        assertThat(tile.payload[0][4]).isEqualTo(".");
        assertThat(tile.payload[0][5]).isEqualTo(".");
        assertThat(tile.payload[0][6]).isEqualTo(".");
        assertThat(tile.payload[0][7]).isEqualTo(".");

        assertThat(tile.payload[1][0]).isEqualTo(".");
        assertThat(tile.payload[1][1]).isEqualTo(".");
        assertThat(tile.payload[1][2]).isEqualTo(".");
        assertThat(tile.payload[1][3]).isEqualTo("#");
        assertThat(tile.payload[1][4]).isEqualTo("#");
        assertThat(tile.payload[1][5]).isEqualTo(".");
        assertThat(tile.payload[1][6]).isEqualTo(".");
        assertThat(tile.payload[1][7]).isEqualTo("#");
    }

    @Test
    public void testSingleRotation() {
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

        var result = tile.rotate(1);

        // Top: 11010010 / 100101100
        assertThat(result.edgesClockwiseFromTop[1].signature).isEqualTo(210);
        assertThat(result.edgesClockwiseFromTop[1].flippedSignature).isEqualTo(300);

        // Right: 1011001 / 1001101000
        assertThat(result.edgesClockwiseFromTop[2].signature).isEqualTo(89);
        assertThat(result.edgesClockwiseFromTop[2].flippedSignature).isEqualTo(616);

        // Bottom: 1110011100 / 11100111
        assertThat(result.edgesClockwiseFromTop[3].signature).isEqualTo(924);
        assertThat(result.edgesClockwiseFromTop[3].flippedSignature).isEqualTo(231);

        // Left: 100111110 / 111110010
        assertThat(result.edgesClockwiseFromTop[0].signature).isEqualTo(318);
        assertThat(result.edgesClockwiseFromTop[0].flippedSignature).isEqualTo(498);


        assertThat(result.payload[0][0]).isEqualTo("#");
        assertThat(result.payload[0][1]).isEqualTo(".");
        assertThat(result.payload[0][2]).isEqualTo("#");
        assertThat(result.payload[0][3]).isEqualTo("#");
        assertThat(result.payload[0][4]).isEqualTo("#");
        assertThat(result.payload[0][5]).isEqualTo("#");
        assertThat(result.payload[0][6]).isEqualTo(".");
        assertThat(result.payload[0][7]).isEqualTo("#");

        assertThat(result.payload[1][0]).isEqualTo("#");
        assertThat(result.payload[1][1]).isEqualTo("#");
        assertThat(result.payload[1][2]).isEqualTo(".");
        assertThat(result.payload[1][3]).isEqualTo(".");
        assertThat(result.payload[1][4]).isEqualTo(".");
        assertThat(result.payload[1][5]).isEqualTo("#");
        assertThat(result.payload[1][6]).isEqualTo(".");
        assertThat(result.payload[1][7]).isEqualTo(".");
    }

    @Test
    public void testCountMonsters() {
        var imageArray = (".####...#####..#...###..\n" +
                "#####..#..#.#.####..#.#.\n" +
                ".#.#...#.###...#.##.##..\n" +
                "#.#.##.###.#.##.##.#####\n" +
                "..##.###.####..#.####.##\n" +
                "...#.#..##.##...#..#..##\n" +
                "#.##.#..#.#..#..##.#.#..\n" +
                ".###.##.....#...###.#...\n" +
                "#.####.#.#....##.#..#.#.\n" +
                "##...#..#....#..#...####\n" +
                "..#.##...###..#.#####..#\n" +
                "....#.##.#.#####....#...\n" +
                "..##.##.###.....#.##..#.\n" +
                "#...#...###..####....##.\n" +
                ".#.##...#.##.#.#.###...#\n" +
                "#.###.#..####...##..#...\n" +
                "#.###...#.##...#.######.\n" +
                ".###.###.#######..#####.\n" +
                "..##.#..#..#.#######.###\n" +
                "#.#..##.########..#..##.\n" +
                "#.#####..#.#...##..#....\n" +
                "#....##..#.#########..##\n" +
                "#...#.....#..##...###.##\n" +
                "#..###....##.#...##.##.#").lines().map(s -> s.split("")).toArray(String[][]::new);
        var solver = new Day20();
        var result = solver.countMonsters(imageArray);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day20();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(273);
    }

    @Test
    public void partTwoRealThing() throws Exception {
        var solver = new Day20();
        InputStream is = Day20.class.getClassLoader().getResourceAsStream("input20.txt");
        var input = new String(is.readAllBytes());
        var result = solver.solvePartTwo(input);
        assertThat(result).isEqualTo(2366);
    }
}
