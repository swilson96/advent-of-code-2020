package uk.co.swilson.advent.day17;

public class ConwayCube {
    public final int x;
    public final int y;
    public final int z;
    public final boolean active;

    public ConwayCube(int x, int y, int z, boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.active = active;
    }

    public String toString() {
        return String.format("[%s,%s,%s,%s]", x, y, z, active);
    }
}
