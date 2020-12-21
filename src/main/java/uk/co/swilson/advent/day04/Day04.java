package uk.co.swilson.advent.day04;

import com.google.common.collect.Lists;
import uk.co.swilson.advent.Solver;

import java.util.Arrays;
import java.util.List;

public class Day04 extends Solver {
    private static final List<String> VALID_ECL_LIST = Lists.newArrayList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    public long solvePartOne(String input) {
        var passports = input.split("\r?\n\r?\n");
        return (int) Arrays.stream(passports)
                .map(passport -> passport.split("\\s+"))
                .filter(this::allRequiredFieldsArePresent)
                .count();
    }

    private boolean allRequiredFieldsArePresent(String[] fields) {
        if (fields.length == 7) {
            // If CID is here, something else is missing
            return !Arrays.stream(fields).anyMatch(f -> f.startsWith("cid"));
        }
        return fields.length == 8;
    }

    public long solvePartTwo(String input) {
        var passports = input.split("\r?\n\r?\n");
        return (int) Arrays.stream(passports)
                .filter(this::passportIsValid)
                .count();
    }

    public boolean passportIsValid(String passport) {
        var fields = passport.split("\\s+");

        if (!allRequiredFieldsArePresent(fields)) {
            return false;
        }

        return Arrays.stream(fields)
                .allMatch(this::fieldIsValid);
    }

    private boolean fieldIsValid(String field) {
        var parts = field.split(":");
        var value = parts[1];
        try {
            switch (parts[0]) {
                case "cid":
                    return true;
                case "byr":
                    var birthYear = Integer.parseInt(value);
                    return 1920 <= birthYear && birthYear <= 2002;
                case "iyr":
                    var issueYear = Integer.parseInt(value);
                    return 2010 <= issueYear && issueYear <= 2020;
                case "eyr":
                    var expirationYear = Integer.parseInt(value);
                    return 2020 <= expirationYear && expirationYear <= 2030;
                case "hgt":
                    return validateHeight(value);
                case "hcl":
                    return value.matches("#[a-f0-9]{6}");
                case "ecl":
                    return VALID_ECL_LIST.contains(value);
                case "pid":
                    return value.matches("\\d{9}");
                default:
                    return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateHeight(String value) {
        if (value.matches("\\d\\d\\dcm")) {
            var height = Integer.parseInt(value.substring(0, 3));
            return 150 <= height && height <= 193;
        }
        if (value.matches("\\d\\din")) {
            var height = Integer.parseInt(value.substring(0, 2));
            return 59 <= height && height <= 76;
        }
        return false;
    }
}
