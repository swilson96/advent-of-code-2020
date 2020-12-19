package uk.co.swilson.advent.day17;

public class GridVector {
    public final int x;
    public final int y;
    public final int z;
    public final int w;

    public GridVector(int x, int y, int z, int w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public String toString() {
        return String.format("(%s,%s,%s,%s)", x, y, z, w);
    }
}
