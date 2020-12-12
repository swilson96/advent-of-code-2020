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
        return switch (type) {
            case "F" -> new Ship(original.direction, original.position.add(original.direction.multiply(amount)));
            case "R" -> new Ship(original.direction.rotateClockwise(amount / 90), original.position);
            case "L" -> new Ship(original.direction.rotateClockwise(-1 * (amount / 90)), original.position);
            case "N" -> new Ship(original.direction, original.position.add(Vector.NORTH.multiply(amount)));
            case "E" -> new Ship(original.direction, original.position.add(Vector.EAST.multiply(amount)));
            case "S" -> new Ship(original.direction, original.position.add(Vector.SOUTH.multiply(amount)));
            case "W" -> new Ship(original.direction, original.position.add(Vector.WEST.multiply(amount)));
            default -> throw new RuntimeException("unknown instruction " + type);
        };
    }

    public Ship actAsPerPartTwo(Ship original) {
        return switch (type) {
            case "F" -> new Ship(original.direction, original.position.add(original.direction.multiply(amount)));
            case "R" -> new Ship(original.direction.rotateClockwise(amount / 90), original.position);
            case "L" -> new Ship(original.direction.rotateClockwise(-1 * (amount / 90)), original.position);
            case "N" -> new Ship(original.direction.add(Vector.NORTH.multiply(amount)), original.position);
            case "E" -> new Ship(original.direction.add(Vector.EAST.multiply(amount)), original.position);
            case "S" -> new Ship(original.direction.add(Vector.SOUTH.multiply(amount)), original.position);
            case "W" -> new Ship(original.direction.add(Vector.WEST.multiply(amount)), original.position);
            default -> throw new RuntimeException("unknown instruction " + type);
        };
    }
}
