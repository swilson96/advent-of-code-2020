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

    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }

    public Vector multiply(int factor) {
        return new Vector(x * factor, y * factor);
    }

    public Vector rotateClockwise(int quarterTurns) {
        quarterTurns = quarterTurns % 4 + 4;
        var result = this;
        for (int i = 0; i < quarterTurns; ++i) {
            result = result.rotateClockwise();
        }
        return result;
    }

    public Vector rotateClockwise() {
        return new Vector(y, -x);
    }

    Vector rotateClockwise(Vector about, int quarterTurns) {
        // translate
        Vector result = this.subtract(about);
        // rotate
        result = result.rotateClockwise(quarterTurns);
        // translate back
        return result.add(about);
    }

    @Override
    public int hashCode() {
        return x + 997 * y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vector)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        var otherVector = (Vector) obj;
        return otherVector.x == x && otherVector.y == y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public static final Vector ORIGIN = new Vector(0, 0);
    public static final Vector NORTH = new Vector(0, 1);
    public static final Vector EAST = new Vector(1, 0);
    public static final Vector SOUTH = new Vector(0, -1);
    public static final Vector WEST = new Vector(-1, 0);
}
