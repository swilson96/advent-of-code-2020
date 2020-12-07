package uk.co.swilson.advent.day07;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Rule {
    private static final Pattern RULE = Pattern.compile("\\d+ (\\S+ \\S+) bags?\\.?");

    public final String parent;
    public final List<String> children;

    public Rule(String input) {
        var bits = input.split(" bags contain ");
        parent = bits[0];
        children = Arrays.stream(bits[1].split(", "))
                .map(bagDesc -> RULE.matcher(bagDesc))
                .map(matcher -> matcher.find() ? matcher.group(1) : null)
                .filter(b -> b != null)
                .collect(Collectors.toList());
    }
}

