package uk.co.swilson.advent.day17;

public class ConwayCube {
    public final int x;
    public final int y;
    public final int z;
    public final int w;
    public final boolean active;

    public ConwayCube(int x, int y, int z, int w, boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.active = active;
    }

    public String toString() {
        return String.format("[%s,%s,%s,%s,%s]", x, y, z, w, active);
    }
}
