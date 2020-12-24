package uk.co.swilson.advent;

import org.junit.jupiter.api.Test;
import uk.co.swilson.advent.day24.Day24;

import java.io.InputStream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Day24Test {
    private static final String EXAMPLE_INPUT = "sesenwnenenewseeswwswswwnenewsewsw\n" +
            "neeenesenwnwwswnenewnwwsewnenwseswesw\n" +
            "seswneswswsenwwnwse\n" +
            "nwnwneseeswswnenewneswwnewseswneseene\n" +
            "swweswneswnenwsewnwneneseenw\n" +
            "eesenwseswswnenwswnwnwsewwnwsene\n" +
            "sewnenenenesenwsewnenwwwse\n" +
            "wenwwweseeeweswwwnwwe\n" +
            "wsweesenenewnwwnwsenewsenwwsesesenwne\n" +
            "neeswseenwwswnwswswnw\n" +
            "nenwswwsewswnenenewsenwsenwnesesenew\n" +
            "enewnwewneswsewnwswenweswnenwsenwsw\n" +
            "sweneswneswneneenwnewenewwneswswnese\n" +
            "swwesenesewenwneswnwwneseswwne\n" +
            "enesenwswwswneneswsenwnewswseenwsese\n" +
            "wnwnesenesenenwwnenwsewesewsesesew\n" +
            "nenewswnwewswnenesenwnesewesw\n" +
            "eneswnwswnwsenenwnwnwwseeswneewsenese\n" +
            "neswnwewnwnwseenwseesewsenwsweewe\n" +
            "wseweeenwnesenwwwswnew";

    @Test
    public void example() {
        var solver = new Day24();
        var result = solver.solvePartOne(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(10);
    }

    @Test
    public void realThing() throws Exception {
        var solver = new Day24();
        InputStream is = Day24.class.getClassLoader().getResourceAsStream("input24.txt");
        var input = new String(is.readAllBytes());
        var result = solver.solvePartOne(input);
        assertThat(result).isEqualTo(488);
    }

    @Test
    public void partTwoExample() {
        var solver = new Day24();
        var result = solver.solvePartTwo(EXAMPLE_INPUT);
        assertThat(result).isEqualTo(2208);
    }
}
