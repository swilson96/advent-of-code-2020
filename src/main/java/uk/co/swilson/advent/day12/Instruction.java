package uk.co.swilson.advent.day12;

import java.util.regex.Pattern;

public class Instruction {
    private static final Pattern INSTRUCTION = Pattern.compile("(\\S)(\\d+)");

    public final String type;
    public final int amount;

    public Instruction(String input) {
        var matcher = INSTRUCTION.matcher(input);
        matcher.find();
        type = matcher.group(1);
        amount = Integer.parseInt(matcher.group(2));
    }

    public Ship actAsPerPartOne(Ship original) {
        switch (type) {
            case "F":
                return new Ship(original.direction, original.position.add(original.direction.multiply(amount)));
            case "R":
                return new Ship(original.direction.rotateClockwise(amount / 90), original.position);
            case "L":
                return new Ship(original.direction.rotateClockwise(-1 * (amount / 90)), original.position);
            case "N":
                return new Ship(original.direction, original.position.add(Vector.NORTH.multiply(amount)));
            case "E":
                return new Ship(original.direction, original.position.add(Vector.EAST.multiply(amount)));
            case "S":
                return new Ship(original.direction, original.position.add(Vector.SOUTH.multiply(amount)));
            case "W":
                return new Ship(original.direction, original.position.add(Vector.WEST.multiply(amount)));
            default:
                throw new RuntimeException("unknown instruction " + type);
        }
    }

    public Ship actAsPerPartTwo(Ship original) {
        switch (type) {
            case "F":
                return new Ship(original.direction, original.position.add(original.direction.multiply(amount)));
            case "R":
                return new Ship(original.direction.rotateClockwise(amount / 90), original.position);
            case "L":
                return new Ship(original.direction.rotateClockwise(-1 * (amount / 90)), original.position);
            case "N":
                return new Ship(original.direction.add(Vector.NORTH.multiply(amount)), original.position);
            case "E":
                return new Ship(original.direction.add(Vector.EAST.multiply(amount)), original.position);
            case "S":
                return new Ship(original.direction.add(Vector.SOUTH.multiply(amount)), original.position);
            case "W":
                return new Ship(original.direction.add(Vector.WEST.multiply(amount)), original.position);
            default:
                throw new RuntimeException("unknown instruction " + type);
        }
    }
}
