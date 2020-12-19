package uk.co.swilson.advent.day17;

public class GridVector {
    public final int x;
    public final int y;
    public final int z;

    public GridVector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return String.format("(%s,%s,%s)", x, y, z);
    }
}
