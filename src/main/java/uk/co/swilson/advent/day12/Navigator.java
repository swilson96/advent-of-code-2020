package uk.co.swilson.advent.day12;

public class Navigator {
    private Ship ship;

    public Navigator(Ship initialShip) {
        this.ship = initialShip;
    }

    public void processPartOneInstruction(Instruction instruction) {
        ship = instruction.actAsPerPartOne(ship);
    }

    public void processPartTwoInstruction(Instruction instruction) {
        ship = instruction.actAsPerPartTwo(ship);
    }

    public int distanceFromOrigin() {
        return ship.position.manhattan(Vector.ORIGIN);
    }
}
