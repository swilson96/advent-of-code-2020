package uk.co.swilson.advent.day12;

public class Ship {
    public final Vector direction;
    public final Vector position;

    public Ship(Vector direction, Vector position) {
        this.direction = direction;
        this.position = position;
    }

    public String toString() {
        return String.format("Ship[%s, %s]", direction.toString(), position.toString());
    }
}
