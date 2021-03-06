package uk.co.swilson.advent.day17;

import java.util.Arrays;
import java.util.Set;

public class PocketDimension {
    private final Boolean[][][][] grid;

    public final GridVector max;

    public PocketDimension(Set<ConwayCube> cubes) {
        var offset = new GridVector(
                cubes.stream().mapToInt(c -> c.x).min().getAsInt(),
                cubes.stream().mapToInt(c -> c.y).min().getAsInt(),
                cubes.stream().mapToInt(c -> c.z).min().getAsInt(),
                cubes.stream().mapToInt(c -> c.w).min().getAsInt()
        );

        this.max = new GridVector(
                cubes.stream().mapToInt(c -> c.x).max().getAsInt() - offset.x,
                cubes.stream().mapToInt(c -> c.y).max().getAsInt() - offset.y,
                cubes.stream().mapToInt(c -> c.z).max().getAsInt() - offset.z,
                cubes.stream().mapToInt(c -> c.w).max().getAsInt() - offset.w
        );

        grid = new Boolean[max.x + 1][max.y + 1][max.z + 1][max.w + 1];
        for (var c : cubes) {
            grid[c.x - offset.x][c.y - offset.y][c.z - offset.z][c.w - offset.w] = c.active;
        }
    }

    public boolean isActive(int x, int y, int z, int w) {
        if (x < 0 || y < 0 || z < 0 ||  w < 0 || x > max.x || y > max.y || z > max.z || w > max.w) {
            return false;
        }
        return grid[x][y][z][w];
    }

    public long numActive() {
        return Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .filter(c -> c)
                .count();
    }
}
