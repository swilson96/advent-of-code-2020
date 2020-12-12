package uk.co.swilson.advent.day12;

public class Navigator {
    private Ship ship = new Ship(Vector.EAST, Vector.ORIGIN);

    public void processInstruction(Instruction instruction) {
        ship = instruction.act(ship);
    }

    public int distanceFromOrigin() {
        return ship.position.manhattan(Vector.ORIGIN);
    }
}
