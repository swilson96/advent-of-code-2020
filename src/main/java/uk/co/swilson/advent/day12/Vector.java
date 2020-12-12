package uk.co.swilson.advent.day12;

public class Vector {
    public final int x;
    public final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int manhattan(Vector other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    public Vector add(int x, int y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }

    public Vector multiply(int factor) {
        return new Vector(x * factor, y * factor);
    }

    public Vector rotateClockwise(int turns) {
        turns = turns % 4 + 4;
        var result = this;
        for (int i = 0; i < turns; ++i) {
            result = result.rotateClockwise();
        }
        return result;
    }

    public Vector rotateClockwise() {
        return new Vector(y, -x);
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public static final Vector ORIGIN = new Vector(0, 0);
    public static final Vector NORTH = new Vector(0, 1);
    public static final Vector EAST = new Vector(1, 0);
    public static final Vector SOUTH = new Vector(0, -1);
    public static final Vector WEST = new Vector(-1, 0);
}
